package dataflow.commons.mappers.enums

import com.dataflow.commons.NodeExecutionPriorityGrpcEnum
import dataflow.commons.dtos.types.NodeExecutionPriorityEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface NodeExecutionPriorityProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: NodeExecutionPriorityGrpcEnum): NodeExecutionPriorityEnum
    fun toProtoEnum(value: NodeExecutionPriorityEnum): NodeExecutionPriorityGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(NodeExecutionPriorityProtoMapper::class.java)
    }

}

fun NodeExecutionPriorityGrpcEnum.toDtoEnum() =
    NodeExecutionPriorityProtoMapper.INSTANCE.toDtoEnum(this)

fun NodeExecutionPriorityEnum.toProtoEnum() =
    NodeExecutionPriorityProtoMapper.INSTANCE.toProtoEnum(this)