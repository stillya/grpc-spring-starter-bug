package dataflow.commons.mappers.enums

import com.dataflow.commons.ExecutionSystemGrpcEnum
import dataflow.commons.dtos.types.ExecutionSystemEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface ExecutionSystemEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: ExecutionSystemGrpcEnum): ExecutionSystemEnum
    fun toProtoEnum(value: ExecutionSystemEnum): ExecutionSystemGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(ExecutionSystemEnumProtoMapper::class.java)
    }

}

fun ExecutionSystemGrpcEnum.toDtoEnum() = ExecutionSystemEnumProtoMapper.INSTANCE.toDtoEnum(this)
fun ExecutionSystemEnum.toProtoEnum() = ExecutionSystemEnumProtoMapper.INSTANCE.toProtoEnum(this)