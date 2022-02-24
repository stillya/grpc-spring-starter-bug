package configconstructor.services.facades

import com.dataflow.configconstructor.enums.ConfigCategory
import configconstructor.mappers.toDto
import configconstructor.mappers.toPojo
import configconstructor.mappers.toPojoEnum
import configconstructor.repositories.ConfigRepository
import dataflow.libconfigconstructor.dto.ConfigCreateDto
import dataflow.libconfigconstructor.dto.ConfigDto
import dataflow.libconfigconstructor.dto.ConfigSearchDto
import dataflow.libconfigconstructor.dto.ConfigUpdateDto
import dataflow.libconfigconstructor.dto.types.ConfigTypeDtoEnum
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@Component
class ConfigService(val configRepository: ConfigRepository) {

    //
    // Read
    // TODO("CRITICAL SECURITY") for not system user content of platform settings must be replaced to ("access denied")
    //

    @Transactional(readOnly = true)
    fun getById(configId: UUID): ConfigDto =
        Optional.ofNullable(configRepository.findById(configId)).orElseThrow().let { it.toDto() }

    @Transactional(readOnly = true)
    fun getAllConfigByTypeAndKind(type: ConfigTypeDtoEnum, kind: String): Collection<ConfigDto> =
        configRepository.getAllByConfigTypeAndKind(type.toPojoEnum(), kind).map { it.toDto() }

    @Transactional(readOnly = true)
    fun getAllConfigByType(type: ConfigTypeDtoEnum): Collection<ConfigDto> =
        configRepository.fetchByConfigType(type.toPojoEnum()).map { it.toDto() }

    @Transactional(readOnly = true)
    fun getConfigsPage(pageable: Pageable, searchDto: ConfigSearchDto): Page<ConfigDto> =
        configRepository.search(pageable, searchDto.type.toPojoEnum(), searchDto.kind).let { page ->
            PageImpl(page.content.map { it.toDto() }, pageable, page.totalElements)
        }

    //
    // Create
    //

    @Transactional
    fun create(configCreateDto: ConfigCreateDto, authorId: UUID?): ConfigDto {
        val configPojo = configCreateDto.toPojo()
        configPojo.creationAuthorId = authorId
        configPojo.configCategory = ConfigCategory.CC_BUSINESS_MODULE_SETTING // others available only via migration
        return configRepository.create(configPojo).let { it.toDto() }
    }

    //
    // Update
    //

    @Transactional
    fun updateById(configUpdateDto: ConfigUpdateDto, authorId: UUID?): ConfigDto {
        val configPojo = configUpdateDto.toPojo()
        configPojo.updatingAuthorId = authorId
        configPojo.updatingDate = OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime()
        return configRepository.modify(configPojo).let { it.toDto() }
    }

    //
    // Delete
    //

    // TODO("CRITICAL SECURITY") platform settings and credentials must not be deleted
    @Transactional
    fun deleteById(configId: UUID) =
        configRepository.deleteById(configId)

}