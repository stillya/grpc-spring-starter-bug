package authservice

import authservice.configurations.properties.KeycloakProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(KeycloakProperties::class)
@SpringBootApplication(scanBasePackages = ["authservice", "dataflow.commons"])
class AuthService

fun main(args: Array<String>) {
    runApplication<AuthService>(*args)
}