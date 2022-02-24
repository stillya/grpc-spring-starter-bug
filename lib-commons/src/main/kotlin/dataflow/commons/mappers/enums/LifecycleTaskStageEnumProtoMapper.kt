package dataflow.commons.mappers.enums

import com.dataflow.commons.LifecycleTaskStageGrpcEnum
import dataflow.commons.dtos.types.LifecycleTaskStageEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface LifecycleTaskStageEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: LifecycleTaskStageGrpcEnum): LifecycleTaskStageEnum
    fun toProtoEnum(value: LifecycleTaskStageEnum): LifecycleTaskStageGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(LifecycleTaskStageEnumProtoMapper::class.java)
    }

}

fun LifecycleTaskStageGrpcEnum.toDtoEnum() =
    LifecycleTaskStageEnumProtoMapper.INSTANCE.toDtoEnum(this)

fun LifecycleTaskStageEnum.toProtoEnum() =
    LifecycleTaskStageEnumProtoMapper.INSTANCE.toProtoEnum(this)