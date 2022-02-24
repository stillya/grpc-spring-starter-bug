package dataflow.commons.mappers.enums

import com.dataflow.commons.NodeExecutionStatusGrpcEnum
import dataflow.commons.dtos.types.NodeExecutionStatusEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface NodeExecutionStatusEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: NodeExecutionStatusGrpcEnum): NodeExecutionStatusEnum
    fun toProtoEnum(value: NodeExecutionStatusEnum): NodeExecutionStatusGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(NodeExecutionStatusEnumProtoMapper::class.java)
    }

}

fun NodeExecutionStatusGrpcEnum.toDtoEnum() =
    NodeExecutionStatusEnumProtoMapper.INSTANCE.toDtoEnum(this)

fun NodeExecutionStatusEnum.toProtoEnum() =
    NodeExecutionStatusEnumProtoMapper.INSTANCE.toProtoEnum(this)