package dataflow.commons.mappers.enums

import com.dataflow.commons.LifecycleStageGrpcEnum
import dataflow.commons.dtos.types.LifecycleStageEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface LifecycleStageEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: LifecycleStageGrpcEnum): LifecycleStageEnum
    fun toProtoEnum(value: LifecycleStageEnum): LifecycleStageGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(LifecycleStageEnumProtoMapper::class.java)
    }

}

fun LifecycleStageGrpcEnum.toDtoEnum() = LifecycleStageEnumProtoMapper.INSTANCE.toDtoEnum(this)
fun LifecycleStageEnum.toProtoEnum() = LifecycleStageEnumProtoMapper.INSTANCE.toProtoEnum(this)