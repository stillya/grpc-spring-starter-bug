package dataflow.commons.mappers.enums

import com.dataflow.commons.LaunchTypeGrpcEnum
import dataflow.commons.dtos.types.LaunchTypeEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface LaunchTypeEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: LaunchTypeGrpcEnum): LaunchTypeEnum
    fun toProtoEnum(value: LaunchTypeEnum): LaunchTypeGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(LaunchTypeEnumProtoMapper::class.java)
    }

}

fun LaunchTypeGrpcEnum.toDtoEnum() = LaunchTypeEnumProtoMapper.INSTANCE.toDtoEnum(this)
fun LaunchTypeEnum.toProtoEnum() = LaunchTypeEnumProtoMapper.INSTANCE.toProtoEnum(this)