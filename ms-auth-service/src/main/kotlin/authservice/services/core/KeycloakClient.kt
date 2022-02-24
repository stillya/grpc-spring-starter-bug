package authservice.services.core

import authservice.configurations.properties.KeycloakProperties
import authservice.dtos.AuthResponseDto
import authservice.dtos.LoginRequestDto
import authservice.dtos.LogoutRequestDto
import authservice.services.core.interfaces.AuthClient
import com.fasterxml.jackson.databind.ObjectMapper
import dataflow.commons.exceptions.AppClientException
import dataflow.commons.exceptions.AppInfrastructureException
import dataflow.commons.utils.Constants
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.awaitBody
import java.util.Base64.Decoder
import javax.annotation.PostConstruct

@Service
class KeycloakClient(
    private val props: KeycloakProperties,
    private val mapper: ObjectMapper,
    private val decoder: Decoder,
) : AuthClient {

    private lateinit var httpClient: WebClient

    companion object {
        const val TOKEN_URI: String = "token"
        const val LOGOUT_URI: String = "logout"
        const val PASSWORD_GRANT_TYPE: String = "password"
        const val REFRESH_TOKEN_GRANT_TYPE: String = "refresh_token"
    }

    @PostConstruct
    fun init() {
        httpClient = WebClient.builder().baseUrl(props.baseUrl)
            .defaultHeader("Content-Type", APPLICATION_FORM_URLENCODED_VALUE).build()
    }

    override suspend fun login(loginDto: LoginRequestDto): AuthResponseDto {
        return try {
            val formData: MultiValueMap<String, String> = LinkedMultiValueMap()
            formData.add("client_id", props.clientId)
            formData.add("username", props.username)
            formData.add("password", props.password)
            formData.add("grant_type", PASSWORD_GRANT_TYPE)
            formData.add("client_secret", props.clientSecret)
            buildResponse(
                httpClient.post().uri(TOKEN_URI).body(BodyInserters.fromFormData(formData))
                    .retrieve().awaitBody()
            )
        } catch (e: WebClientResponseException) {
            when (e.statusCode) {
                HttpStatus.UNAUTHORIZED -> throw AppClientException.AppUnauthenticatedException(
                    e,
                    "Invalid username or password. Verify the data is correct and try again."
                )
                else -> throw AppInfrastructureException.AppExternalSystemUnavailableException(e)
            }
        }
    }

    override suspend fun refreshToken(refreshToken: String): AuthResponseDto {
        return try {
            val formData: MultiValueMap<String, String> = LinkedMultiValueMap()
            formData.add("client_id", props.clientId)
            formData.add("username", props.username)
            formData.add("password", props.password)
            formData.add("grant_type", REFRESH_TOKEN_GRANT_TYPE)
            formData.add("client_secret", props.clientSecret)
            formData.add("refresh_token", refreshToken)
            buildResponse(
                httpClient.post().uri(TOKEN_URI).body(BodyInserters.fromFormData(formData))
                    .retrieve().awaitBody()
            )
        } catch (e: WebClientResponseException) {
            when (e.statusCode) {
                HttpStatus.UNAUTHORIZED -> throw AppClientException.AppUnauthenticatedException(
                    e,
                    "Invalid refresh token. Verify the data is correct and try again"
                )
                else -> throw AppInfrastructureException.AppExternalSystemUnavailableException(e)
            }
        }
    }

    override suspend fun logout(logoutRequestDto: LogoutRequestDto) {
        try {
            val formData: MultiValueMap<String, String> = LinkedMultiValueMap()
            formData.add("client_id", props.clientId)
            formData.add("refresh_token", logoutRequestDto.refreshToken)
            formData.add("client_secret", props.clientSecret)
            httpClient.post().uri(LOGOUT_URI).body(BodyInserters.fromFormData(formData))
                .header("Authorization", "${Constants.BEARER} ${logoutRequestDto.accessToken}")
                .retrieve()
        } catch (e: WebClientResponseException) {
            when (e.statusCode) {
                HttpStatus.UNAUTHORIZED -> throw AppClientException.AppUnauthenticatedException(
                    e,
                    "Invalid refresh of access token. Verify the data is correct and try again"
                )
                else -> throw AppInfrastructureException.AppExternalSystemUnavailableException(e)
            }
        }
    }


    private fun buildResponse(dto: AuthResponseDto): AuthResponseDto {
        val json = mapper.readTree(decoder.decode(dto.accessToken.split(".").toTypedArray()[1]))
        return AuthResponseDto(
            dto.accessToken,
            dto.expiresIn,
            dto.refreshExpiresIn,
            dto.refreshToken,
            dto.tokenType,
            dto.notBeforePolicy,
            dto.sessionState,
            dto.scope,
            json.get("realm_access").get("roles").map { jsonNode -> jsonNode.asText() },
            json.get("sub").asText(),
            json.get("preferred_username").asText()
        )
    }
}