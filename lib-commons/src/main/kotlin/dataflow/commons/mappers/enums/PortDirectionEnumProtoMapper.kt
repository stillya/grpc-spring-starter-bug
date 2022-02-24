package dataflow.commons.mappers.enums

import com.dataflow.commons.PortDirectionGrpcEnum
import dataflow.commons.dtos.types.PortDirectionEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface PortDirectionEnumProtoMapper {

    fun toProtoEnum(value: PortDirectionEnum): PortDirectionGrpcEnum

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: PortDirectionGrpcEnum): PortDirectionEnum

    companion object {
        val INSTANCE = Mappers.getMapper(PortDirectionEnumProtoMapper::class.java)
    }

}

fun PortDirectionEnum.toProtoEnum() = PortDirectionEnumProtoMapper.INSTANCE.toProtoEnum(this)
fun PortDirectionGrpcEnum.toDtoEnum() = PortDirectionEnumProtoMapper.INSTANCE.toDtoEnum(this)