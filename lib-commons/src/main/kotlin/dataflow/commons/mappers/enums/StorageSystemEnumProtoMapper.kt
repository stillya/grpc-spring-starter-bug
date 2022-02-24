package dataflow.commons.mappers.enums

import com.dataflow.commons.StorageSystemGrpcEnum
import dataflow.commons.dtos.types.StorageSystemEnum
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface StorageSystemEnumProtoMapper {

    @ValueMappings(ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.THROW_EXCEPTION))
    fun toDtoEnum(value: StorageSystemGrpcEnum): StorageSystemEnum
    fun toProtoEnum(value: StorageSystemEnum): StorageSystemGrpcEnum

    companion object {
        val INSTANCE = Mappers.getMapper(StorageSystemEnumProtoMapper::class.java)
    }

}

fun StorageSystemGrpcEnum.toDtoEnum() = StorageSystemEnumProtoMapper.INSTANCE.toDtoEnum(this)
fun StorageSystemEnum.toProtoEnum() = StorageSystemEnumProtoMapper.INSTANCE.toProtoEnum(this)