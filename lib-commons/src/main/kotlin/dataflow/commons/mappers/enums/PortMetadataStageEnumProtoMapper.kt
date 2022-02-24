package dataflow.commons.mappers.enums

import com.dataflow.commons.PortMetadataStageGrpcEnum
import dataflow.commons.dtos.types.PortMetadataStageEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface PortMetadataStageEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: PortMetadataStageGrpcEnum): PortMetadataStageEnum
    fun toProtoEnum(value: PortMetadataStageEnum): PortMetadataStageGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(PortMetadataStageEnumProtoMapper::class.java)
    }

}

fun PortMetadataStageGrpcEnum.toDtoEnum() =
    PortMetadataStageEnumProtoMapper.INSTANCE.toDtoEnum(this)

fun PortMetadataStageEnum.toProtoEnum() =
    PortMetadataStageEnumProtoMapper.INSTANCE.toProtoEnum(this)