package authservice.services

import authservice.dtos.AuthResponseDto
import authservice.dtos.LoginRequestDto
import authservice.dtos.LogoutRequestDto
import authservice.services.core.interfaces.AuthClient
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val authClient: AuthClient) {

    companion object : KLogging()

    suspend fun login(loginRequestDto: LoginRequestDto): AuthResponseDto {
        logger.debug { "Logging user ${loginRequestDto.username}" }
        return authClient.login(loginRequestDto)
    }

    suspend fun refreshToken(refreshToken: String): AuthResponseDto {
        logger.debug { "Refreshing token" }
        return authClient.refreshToken(refreshToken)
    }

    suspend fun logout(logoutRequestDto: LogoutRequestDto) {
        logger.debug { "Logout user" }
        return authClient.logout(logoutRequestDto)
    }
}