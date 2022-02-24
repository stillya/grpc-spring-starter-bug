package dataflow.commons.dtos.types

enum class WorkflowPurposeEnum(val value: String) {
    WP_MARKETING_CAMPAIGN("MARKETING_CAMPAIGN"),
    WP_MODEL("MODEL"),
    WP_MODEL_VALIDATOR("MODEL_VALIDATOR"),
    WP_MODEL_LEARNER("MODEL_LEARNER"),
    WP_STRATEGY("STRATEGY"),
}