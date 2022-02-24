package dataflow.commons.dtos.types

enum class LifecycleStageEnum(val value: String) {
    LS_CREATING("CREATING"),
    LS_CREATED("CREATED"),
    LS_DELETING("DELETING"),
    LS_DELETED("DELETED")
}