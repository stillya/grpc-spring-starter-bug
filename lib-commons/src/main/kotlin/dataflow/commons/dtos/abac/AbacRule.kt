package dataflow.commons.dtos.abac

data class AbacRule(
    val name: String,
    val description: String,
    val condition: String
)