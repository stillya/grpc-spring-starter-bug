package dataflow.libconfigconstructor.mappers

import com.dataflow.libconfigconstructor.dto.ConfigCreateGrpcDto
import com.dataflow.libconfigconstructor.dto.ConfigGrpcDto
import com.dataflow.libconfigconstructor.dto.ConfigUpdateGrpcDto
import dataflow.libconfigconstructor.dto.ConfigCreateDto
import dataflow.libconfigconstructor.dto.ConfigDto
import dataflow.libconfigconstructor.dto.ConfigUpdateDto
import org.mapstruct.CollectionMappingStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface ConfigProtoMapper : com.dataflow.mappers.AbstractBasicProtoMapper {
    fun toProto(config: ConfigDto): ConfigGrpcDto
    fun toDto(config: ConfigGrpcDto): ConfigDto
    fun toCreateDto(config: ConfigCreateGrpcDto): ConfigCreateDto
    fun toUpdateDto(config: ConfigUpdateGrpcDto): ConfigUpdateDto

    companion object {
        val INSTANCE = Mappers.getMapper(ConfigProtoMapper::class.java)
    }
}