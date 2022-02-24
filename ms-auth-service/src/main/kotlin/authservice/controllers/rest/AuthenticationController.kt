package authservice.controllers.rest

//import org.springframework.security.access.prepost.PreAuthorize
import authservice.dtos.AuthResponseDto
import authservice.dtos.LoginRequestDto
import authservice.dtos.LogoutRequestDto
import authservice.services.AuthenticationService
import dataflow.commons.security.AuthorizationGrpcClient
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/authenticate")
class AuthenticationController(private val authenticationService: AuthenticationService, private val client: AuthorizationGrpcClient) {

    @GetMapping
    fun test(): String {
        val test = client.getRuleByName("test")
        return test.condition
    }

    //    @PreAuthorize("hasPermission(#loginRequestDto, 'test')")
    @PostMapping("/login")
    suspend fun login(@Valid @RequestBody loginRequestDto: LoginRequestDto): AuthResponseDto =
        authenticationService.login(loginRequestDto)

    @PostMapping("/refresh")
    suspend fun refreshToken(@RequestBody refreshToken: String): AuthResponseDto =
        authenticationService.refreshToken(refreshToken)

    @PostMapping("/logout")
    suspend fun logout(@RequestBody logoutRequestDto: LogoutRequestDto) =
        authenticationService.logout(logoutRequestDto)

}
