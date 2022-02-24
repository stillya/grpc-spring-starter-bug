package authservice.controllers.rest

import authservice.services.AuthorizationService
import dataflow.commons.dtos.abac.AbacRule
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/authorize")
class AuthorizationController(private val authorizationService: AuthorizationService) {

    @GetMapping
    fun getRules(): Flux<AbacRule> = authorizationService.getAllRules()

    @GetMapping("/{name}")
    suspend fun getRuleByName(@PathVariable("name") name: String): AbacRule? =
        authorizationService.getRuleByName(name)

}