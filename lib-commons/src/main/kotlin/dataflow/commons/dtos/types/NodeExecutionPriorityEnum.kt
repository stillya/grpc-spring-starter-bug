package dataflow.commons.dtos.types

enum class NodeExecutionPriorityEnum(val value: String) {
    EP_AUTO("AUTO"), // from system config
    EP_FAST_SPEED("FAST_SPEED"),
    EP_LOW_MEMORY("LOW_MEMORY"),
}