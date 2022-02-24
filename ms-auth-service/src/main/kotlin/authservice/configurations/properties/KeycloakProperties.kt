package authservice.configurations.properties

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated

@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "keycloak")
@ConditionalOnProperty(prefix = "keycloak", havingValue = "enabled")
data class KeycloakProperties(
    val realm: String,
    val clientId: String,
    val clientSecret: String,
    val username: String,
    val password: String,
    val baseUrl: String
)
