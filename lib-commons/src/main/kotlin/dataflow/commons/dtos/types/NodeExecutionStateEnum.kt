package dataflow.commons.dtos.types

enum class NodeExecutionStateEnum(val value: String) {
    NEST_INITIAL("INITIAL"),
    NEST_SYSTEM_ERROR("SYSTEM_ERROR"),
    NEST_BUSINESS_ERROR("BUSINESS_ERROR"),
    NEST_CONVERSION_ERROR("CONVERSION_ERROR"),
    NEST_HOOK_ERROR("HOOK_ERROR"),
    NEST_ABORTED("ABORTED"),
    NEST_SUCCESS("SUCCESS"),
}