package authservice.dtos

data class LogoutRequestDto(val accessToken: String = "", val refreshToken: String = "")