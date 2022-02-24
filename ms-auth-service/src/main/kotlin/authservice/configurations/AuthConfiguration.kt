package authservice.configurations

import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.core.DatabaseClient
import java.util.*

@Configuration
class AuthConfiguration {

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper()

    @Bean
    fun databaseClient(connectionFactory: ConnectionFactory): DatabaseClient =
        DatabaseClient.create(connectionFactory)
}