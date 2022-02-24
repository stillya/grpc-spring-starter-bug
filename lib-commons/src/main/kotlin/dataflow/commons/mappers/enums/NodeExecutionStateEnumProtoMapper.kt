package dataflow.commons.mappers.enums

import com.dataflow.commons.NodeExecutionStateGrpcEnum
import dataflow.commons.dtos.types.NodeExecutionStateEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface NodeExecutionStateEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: NodeExecutionStateGrpcEnum): NodeExecutionStateEnum
    fun toProtoEnum(value: NodeExecutionStateEnum): NodeExecutionStateGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(NodeExecutionStateEnumProtoMapper::class.java)
    }

}

fun NodeExecutionStateGrpcEnum.toDtoEnum() =
    NodeExecutionStateEnumProtoMapper.INSTANCE.toDtoEnum(this)

fun NodeExecutionStateEnum.toProtoEnum() =
    NodeExecutionStateEnumProtoMapper.INSTANCE.toProtoEnum(this)