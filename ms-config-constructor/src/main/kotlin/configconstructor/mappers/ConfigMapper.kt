package configconstructor.mappers

import com.dataflow.configconstructor.tables.pojos.Config
import dataflow.libconfigconstructor.dto.ConfigCreateDto
import dataflow.libconfigconstructor.dto.ConfigDto
import dataflow.libconfigconstructor.dto.ConfigUpdateDto
import org.mapstruct.CollectionMappingStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface ConfigMapper : com.dataflow.mappers.AbstractBasicProtoMapper {
    @Mapping(source = "content", target = "content", ignore = true)
    fun toDto(config: Config): ConfigDto
    @Mapping(source = "content", target = "content", ignore = true)
    fun toPojo(config: ConfigCreateDto): Config
    @Mapping(source = "content", target = "content", ignore = true)
    fun toPojo(config: ConfigUpdateDto): Config

    companion object {
        val INSTANCE = Mappers.getMapper(ConfigMapper::class.java)
    }
}

fun Config.toDto() = ConfigMapper.INSTANCE.toDto(this)
fun ConfigCreateDto.toPojo() = ConfigMapper.INSTANCE.toPojo(this)
fun ConfigUpdateDto.toPojo() = ConfigMapper.INSTANCE.toPojo(this)