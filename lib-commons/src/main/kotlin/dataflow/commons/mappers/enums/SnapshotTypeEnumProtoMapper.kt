package dataflow.commons.mappers.enums

import com.dataflow.commons.SnapshotTypeGrpcEnum
import dataflow.commons.dtos.types.SnapshotTypeEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface SnapshotTypeEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: SnapshotTypeGrpcEnum): SnapshotTypeEnum
    fun toProtoEnum(value: SnapshotTypeEnum): SnapshotTypeGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(SnapshotTypeEnumProtoMapper::class.java)
    }

}

fun SnapshotTypeGrpcEnum.toDtoEnum() = SnapshotTypeEnumProtoMapper.INSTANCE.toDtoEnum(this)
fun SnapshotTypeEnum.toProtoEnum() = SnapshotTypeEnumProtoMapper.INSTANCE.toProtoEnum(this)