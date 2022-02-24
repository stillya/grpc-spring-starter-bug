package dataflow.commons.dtos.types

enum class WorkflowExecutionStatusEnum(val value: String) {
    WESS_IDLE("IDLE"),
    WESS_LAUNCHING("LAUNCHING"),
    WESS_EXECUTING("EXECUTING"),
    WESS_FINISHING("FINISHING"),
    WESS_ABORTING("ABORTING"),
}