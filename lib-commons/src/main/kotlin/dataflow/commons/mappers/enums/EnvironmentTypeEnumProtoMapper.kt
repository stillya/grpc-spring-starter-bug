package dataflow.commons.mappers.enums

import com.dataflow.commons.EnvironmentTypeGrpcEnum
import dataflow.commons.dtos.types.EnvironmentTypeEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface EnvironmentTypeEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: EnvironmentTypeGrpcEnum): EnvironmentTypeEnum
    fun toProtoEnum(value: EnvironmentTypeEnum): EnvironmentTypeGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(EnvironmentTypeEnumProtoMapper::class.java)
    }

}

fun EnvironmentTypeGrpcEnum.toDtoEnum() = EnvironmentTypeEnumProtoMapper.INSTANCE.toDtoEnum(this)
fun EnvironmentTypeEnum.toProtoEnum() = EnvironmentTypeEnumProtoMapper.INSTANCE.toProtoEnum(this)