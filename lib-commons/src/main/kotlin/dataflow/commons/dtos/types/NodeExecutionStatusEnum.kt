package dataflow.commons.dtos.types

enum class NodeExecutionStatusEnum(val value: String) {
    NESS_IDLE("IDLE"),
    NESS_LAUNCHING("LAUNCHING"),
    NESS_CONVERTING("CONVERTING"),
    NESS_EXECUTING("EXECUTING"),
    NESS_FINISHING("FINISHING"),
    NESS_ABORTING("ABORTING"),
}