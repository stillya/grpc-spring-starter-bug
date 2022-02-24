package dataflow.commons.mappers.enums

import com.dataflow.commons.NodeSettingsStageGrpcEnum
import dataflow.commons.dtos.types.NodeSettingsStageEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface NodeSettingsStageProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: NodeSettingsStageGrpcEnum): NodeSettingsStageEnum
    fun toProtoEnum(value: NodeSettingsStageEnum): NodeSettingsStageGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(NodeSettingsStageProtoMapper::class.java)
    }

}

fun NodeSettingsStageGrpcEnum.toDtoEnum() = NodeSettingsStageProtoMapper.INSTANCE.toDtoEnum(this)
fun NodeSettingsStageEnum.toProtoEnum() = NodeSettingsStageProtoMapper.INSTANCE.toProtoEnum(this)