package dataflow.commons.dtos.types

enum class ExecutionSystemEnum(val value: String) {
    ES_HOOKS_ONLY("HOOKS_ONLY"),
    ES_DOCKER("DOCKER"),
    ES_GROOVY_SCRIPT("GROOVY_SCRIPT"),
    ES_SQL_SCRIPT("SQL_SCRIPT"),
}