package dataflow.commons.dtos.types

enum class LifecycleTaskStageEnum(val value: String) {
    LTS_SCHEDULED("SCHEDULED"),
    LTS_EXECUTING("EXECUTING"),
    LTS_EXECUTED("EXECUTED"),
    LTS_FAILED("FAILED"),
    LTS_ABORTING("ABORTING"),
    LTS_ABORTED("ABORTED"),
}