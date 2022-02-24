package dataflow.libconfigconstructor.dto

import dataflow.libconfigconstructor.dto.types.ConfigTypeDtoEnum

data class ConfigSearchDto(val type: ConfigTypeDtoEnum, val kind: String?)
