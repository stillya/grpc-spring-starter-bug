package authservice.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthResponseDto(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("expires_in") val expiresIn: Int,
    @JsonProperty("refresh_expires_in") val refreshExpiresIn: Int,
    @JsonProperty("refresh_token") val refreshToken: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("not-before-policy") val notBeforePolicy: Int,
    @JsonProperty("session_state") val sessionState: String,
    val scope: String?,
    val roles: List<String>?,
    val id: String?,
    val username: String?
)