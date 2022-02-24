package configconstructor.mappers

import com.dataflow.configconstructor.enums.ConfigType
import dataflow.libconfigconstructor.dto.types.ConfigTypeDtoEnum
import org.mapstruct.CollectionMappingStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface ConfigTypeEnumMapper {
    fun toProto(config: ConfigType): ConfigTypeDtoEnum
    fun toPojoEnum(config: ConfigTypeDtoEnum): ConfigType

    companion object {
        val INSTANCE = Mappers.getMapper(ConfigTypeEnumMapper::class.java)
    }
}

fun ConfigTypeDtoEnum.toPojoEnum() = ConfigTypeEnumMapper.INSTANCE.toPojoEnum(this)