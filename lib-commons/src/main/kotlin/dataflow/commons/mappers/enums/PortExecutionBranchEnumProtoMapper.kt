package dataflow.commons.mappers.enums

import com.dataflow.commons.PortExecutionBranchGrpcEnum
import dataflow.commons.dtos.types.PortExecutionBranchEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface PortExecutionBranchEnumProtoMapper {

    fun toProtoEnum(value: PortExecutionBranchEnum): PortExecutionBranchGrpcEnum

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: PortExecutionBranchGrpcEnum): PortExecutionBranchEnum

    companion object {
        val INSTANCE = Mappers.getMapper(PortExecutionBranchEnumProtoMapper::class.java)
    }

}

fun PortExecutionBranchEnum.toProtoEnum() =
    PortExecutionBranchEnumProtoMapper.INSTANCE.toProtoEnum(this)

fun PortExecutionBranchGrpcEnum.toDtoEnum() =
    PortExecutionBranchEnumProtoMapper.INSTANCE.toDtoEnum(this)