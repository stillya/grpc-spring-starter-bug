package dataflow.libconfigconstructor.dto

import com.dataflow.libconfigconstructor.dto.ConfigGrpcDto
import dataflow.libconfigconstructor.dto.types.ConfigCategoryEnum
import dataflow.libconfigconstructor.dto.types.ConfigTypeDtoEnum
import dataflow.libconfigconstructor.mappers.ConfigProtoMapper
import java.util.*

data class ConfigDto(
    val configId: UUID,
    val type: ConfigTypeDtoEnum,
    val kind: String,
    val content: String,
    val category: ConfigCategoryEnum
)

fun ConfigDto.toProto() = ConfigProtoMapper.INSTANCE.toProto(this)
fun ConfigGrpcDto.toDto() = ConfigProtoMapper.INSTANCE.toDto(this)