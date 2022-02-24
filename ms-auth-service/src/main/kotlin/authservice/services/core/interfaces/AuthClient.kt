package authservice.services.core.interfaces

import authservice.dtos.AuthResponseDto
import authservice.dtos.LoginRequestDto
import authservice.dtos.LogoutRequestDto

interface AuthClient {
    suspend fun login(loginDto: LoginRequestDto): AuthResponseDto

    suspend fun refreshToken(refreshToken: String): AuthResponseDto

    suspend fun logout(logoutRequestDto: LogoutRequestDto)
}