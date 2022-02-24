package dataflow.commons.mappers.enums

import com.dataflow.commons.WorkflowExecutionStateGrpcEnum
import dataflow.commons.dtos.types.WorkflowExecutionStateEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface WorkflowExecutionStateEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: WorkflowExecutionStateGrpcEnum): WorkflowExecutionStateEnum
    fun toProtoEnum(value: WorkflowExecutionStateEnum): WorkflowExecutionStateGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(WorkflowExecutionStateEnumProtoMapper::class.java)
    }

}

fun WorkflowExecutionStateGrpcEnum.toDtoEnum() =
    WorkflowExecutionStateEnumProtoMapper.INSTANCE.toDtoEnum(this)

fun WorkflowExecutionStateEnum.toProtoEnum() =
    WorkflowExecutionStateEnumProtoMapper.INSTANCE.toProtoEnum(this)