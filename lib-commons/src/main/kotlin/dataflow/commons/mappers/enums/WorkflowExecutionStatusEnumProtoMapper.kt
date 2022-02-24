package dataflow.commons.mappers.enums

import com.dataflow.commons.WorkflowExecutionStatusGrpcEnum
import dataflow.commons.dtos.types.WorkflowExecutionStatusEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface WorkflowExecutionStatusEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: WorkflowExecutionStatusGrpcEnum): WorkflowExecutionStatusEnum
    fun toProtoEnum(value: WorkflowExecutionStatusEnum): WorkflowExecutionStatusGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(WorkflowExecutionStatusEnumProtoMapper::class.java)
    }

}

fun WorkflowExecutionStatusGrpcEnum.toDtoEnum() =
    WorkflowExecutionStatusEnumProtoMapper.INSTANCE.toDtoEnum(this)

fun WorkflowExecutionStatusEnum.toProtoEnum() =
    WorkflowExecutionStatusEnumProtoMapper.INSTANCE.toProtoEnum(this)