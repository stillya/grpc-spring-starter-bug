package dataflow.libconfigconstructor.dto.types

import com.dataflow.libconfigconstructor.dto.ConfigTypeGrpcEnum
import dataflow.libconfigconstructor.mappers.ConfigTypeEnumMapper

enum class ConfigTypeDtoEnum(val value: String) {
    CT_SCHEMA("SCHEMA"),
    CT_JSON_EDITOR("JSON_EDITOR"),
    CT_JSON_CONSTRUCTOR("JSON_CONSTRUCTOR"),
    CT_EXTERNAL_SYSTEM("EXTERNAL_SYSTEM"),
//    CT_TEXT("TEXT"),
    CT_SMS_TEMPLATE("SMS"),
    CT_EMAIL_TEMPLATE("EMAIL"),
}

fun ConfigTypeDtoEnum.toProto() = ConfigTypeEnumMapper.INSTANCE.toProto(this)
fun ConfigTypeGrpcEnum.toDtoEnum() = ConfigTypeEnumMapper.INSTANCE.toDtoEnum(this)