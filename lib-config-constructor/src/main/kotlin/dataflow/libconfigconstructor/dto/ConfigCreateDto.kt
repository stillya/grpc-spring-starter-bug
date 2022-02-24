package dataflow.libconfigconstructor.dto

import com.dataflow.libconfigconstructor.dto.ConfigCreateGrpcDto
import dataflow.libconfigconstructor.dto.types.ConfigTypeDtoEnum
import dataflow.libconfigconstructor.mappers.ConfigProtoMapper

data class  ConfigCreateDto(val type: ConfigTypeDtoEnum, val kind: String, val content: String)

fun ConfigCreateGrpcDto.toCreateDto() = ConfigProtoMapper.INSTANCE.toCreateDto(this)