package dataflow.libconfigconstructor.dto.types

enum class ConfigCategoryEnum(val value: String) {
    CC_PLATFORM_CREDENTIALS("PLATFORM_CREDENTIALS"), // only devops admin could change
    CC_PLATFORM_SETTING("PLATFORM_SETTING"), // only business admin could change
    CC_BUSINESS_MODULE_SETTING("BUSINESS_MODULE_SETTING"), // business module developers could change
}