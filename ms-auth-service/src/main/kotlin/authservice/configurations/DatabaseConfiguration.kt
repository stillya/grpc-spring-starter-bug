package authservice.configurations

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration

import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.transaction.ReactiveTransactionManager

@Configuration
@EnableR2dbcRepositories
class DatabaseConfiguration : AbstractR2dbcConfiguration() {

    @Value("\${spring.r2dbc.database}")
    lateinit var database: String

    @Value("\${spring.r2dbc.host}")
    lateinit var host: String

    @Value("\${spring.r2dbc.username}")
    lateinit var username: String

    @Value("\${spring.r2dbc.password}")
    lateinit var password: String

    @Value("\${spring.r2dbc.port}")
    lateinit var port: Integer

    @Value("\${spring.r2dbc.schema}")
    lateinit var schema: String

    @Value("\${spring.r2dbc.applicationName}")
    lateinit var applicationName: String

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .database(database)
                .host(host)
                .username(username)
                .password(password)
                .port(port.toInt())
                .schema(schema)
                .applicationName(applicationName)
                .build()

        )
    }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }
}