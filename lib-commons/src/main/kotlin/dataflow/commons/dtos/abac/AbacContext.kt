package dataflow.commons.dtos.abac

import dataflow.commons.dtos.UserDto

data class AbacContext(
    val subject: UserDto,
    val domainObject: Any,
    val action: String,
    val environment: AbacEnvironment
)