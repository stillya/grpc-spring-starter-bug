package dataflow.commons.dtos

import java.util.*

data class UserDto(val id: UUID, val username: String, val roles: List<String>)
