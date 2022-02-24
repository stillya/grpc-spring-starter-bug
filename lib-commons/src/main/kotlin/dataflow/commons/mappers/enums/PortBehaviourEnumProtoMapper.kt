package dataflow.commons.mappers.enums

import com.dataflow.commons.PortBehaviourGrpcEnum
import dataflow.commons.dtos.types.PortBehaviourEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface PortBehaviourEnumProtoMapper {

    fun toProtoEnum(value: PortBehaviourEnum): PortBehaviourGrpcEnum

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: PortBehaviourGrpcEnum): PortBehaviourEnum

    companion object {
        val INSTANCE = Mappers.getMapper(PortBehaviourEnumProtoMapper::class.java)
    }

}

fun PortBehaviourEnum.toProtoEnum() = PortBehaviourEnumProtoMapper.INSTANCE.toProtoEnum(this)
fun PortBehaviourGrpcEnum.toDtoEnum() = PortBehaviourEnumProtoMapper.INSTANCE.toDtoEnum(this)