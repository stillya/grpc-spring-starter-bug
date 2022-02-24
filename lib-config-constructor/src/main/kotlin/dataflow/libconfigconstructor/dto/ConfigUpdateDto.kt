package dataflow.libconfigconstructor.dto

import com.dataflow.libconfigconstructor.dto.ConfigUpdateGrpcDto
import dataflow.libconfigconstructor.dto.types.ConfigTypeDtoEnum
import dataflow.libconfigconstructor.mappers.ConfigProtoMapper
import java.util.*

data class ConfigUpdateDto(
    val configId: UUID,
    val type: ConfigTypeDtoEnum,
    val kind: String,
    val content: String
)

fun ConfigUpdateGrpcDto.toUpdateDto() = ConfigProtoMapper.INSTANCE.toUpdateDto(this)