package dataflow.commons.mappers.enums

import com.dataflow.commons.ExecutionTypeGrpcEnum
import dataflow.commons.dtos.types.ExecutionTypeEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface ExecutionTypeEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: ExecutionTypeGrpcEnum): ExecutionTypeEnum
    fun toProtoEnum(value: ExecutionTypeEnum): ExecutionTypeGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(ExecutionTypeEnumProtoMapper::class.java)
    }

}

fun ExecutionTypeGrpcEnum.toDtoEnum() = ExecutionTypeEnumProtoMapper.INSTANCE.toDtoEnum(this)
fun ExecutionTypeEnum.toProtoEnum() = ExecutionTypeEnumProtoMapper.INSTANCE.toProtoEnum(this)