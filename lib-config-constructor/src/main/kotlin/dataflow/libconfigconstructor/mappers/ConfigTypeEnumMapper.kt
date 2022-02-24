package dataflow.libconfigconstructor.mappers

import com.dataflow.libconfigconstructor.dto.ConfigTypeGrpcEnum
import dataflow.libconfigconstructor.dto.types.ConfigTypeDtoEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface ConfigTypeEnumMapper {
    fun toProto(config: ConfigTypeDtoEnum): ConfigTypeGrpcEnum

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(config: ConfigTypeGrpcEnum): ConfigTypeDtoEnum

    companion object {
        val INSTANCE = Mappers.getMapper(ConfigTypeEnumMapper::class.java)
    }
}