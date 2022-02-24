package dataflow.commons.mappers.enums

import com.dataflow.commons.WorkflowPurposeGrpcEnum
import dataflow.commons.dtos.types.WorkflowPurposeEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface WorkflowPurposeEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: WorkflowPurposeGrpcEnum): WorkflowPurposeEnum
    fun toProtoEnum(value: WorkflowPurposeEnum): WorkflowPurposeGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(WorkflowPurposeEnumProtoMapper::class.java)
    }

}

fun WorkflowPurposeGrpcEnum.toDtoEnum() = WorkflowPurposeEnumProtoMapper.INSTANCE.toDtoEnum(this)
fun WorkflowPurposeEnum.toProtoEnum() = WorkflowPurposeEnumProtoMapper.INSTANCE.toProtoEnum(this)