package dataflow.commons.mappers.enums

import com.dataflow.commons.PortDataTypeGrpcEnum
import dataflow.commons.dtos.types.PortDataTypeEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface PortDataTypeEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: PortDataTypeGrpcEnum): PortDataTypeEnum
    fun toProtoEnum(value: PortDataTypeEnum): PortDataTypeGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(PortDataTypeEnumProtoMapper::class.java)
    }

}

fun PortDataTypeGrpcEnum.toDtoEnum() = PortDataTypeEnumProtoMapper.INSTANCE.toDtoEnum(this)
fun PortDataTypeEnum.toProtoEnum() = PortDataTypeEnumProtoMapper.INSTANCE.toProtoEnum(this)