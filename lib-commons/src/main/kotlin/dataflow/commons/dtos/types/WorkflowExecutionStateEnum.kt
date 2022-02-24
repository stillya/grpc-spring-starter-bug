package dataflow.commons.dtos.types

enum class WorkflowExecutionStateEnum(val value: String) {
    WEST_INITIAL("INITIAL"),
    WEST_SYSTEM_ERROR("SYSTEM_ERROR"),
    WEST_NODES_ERROR("NODES_ERROR"),
    WEST_ABORTED("ABORTED"),
    WEST_SUCCESS("SUCCESS"),
}