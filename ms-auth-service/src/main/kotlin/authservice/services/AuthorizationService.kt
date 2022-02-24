package authservice.services

import dataflow.commons.dtos.abac.AbacRule
import dataflow.commons.exceptions.AppInfrastructureException
import io.r2dbc.spi.Row
import io.r2dbc.spi.RowMetadata
import mu.KLogging
import org.springframework.dao.DataAccessResourceFailureException
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitOneOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.function.BiFunction


@Service
class AuthorizationService(val databaseClient: DatabaseClient) {

    companion object : KLogging()

    private val mappingFunction: BiFunction<Row, RowMetadata, AbacRule> =
        BiFunction<Row, RowMetadata, AbacRule> { row: Row, _: RowMetadata? ->
            AbacRule(
                row.get("name", String::class.java),
                row.get("description", String::class.java),
                row.get("condition", String::class.java)
            )
        }

    suspend fun getRuleByName(name: String): AbacRule? {
        try {
            return databaseClient.sql("SELECT * FROM abac_rule WHERE name = :name")
                .bind("name", name).map(mappingFunction).awaitOneOrNull()
        } catch (e: DataAccessResourceFailureException) {
            throw AppInfrastructureException.AppDatabaseUnavailableException(e)
        }
    }

    fun getAllRules(): Flux<AbacRule> {
        return databaseClient.sql("SELECT * FROM abac_rule").map(mappingFunction).all()
            .onErrorResume {
                when (it) {
                    is DataAccessResourceFailureException -> throw AppInfrastructureException.AppDatabaseUnavailableException(
                        it
                    )
                    else -> {
                        throw it
                    }
                }
            }

    }
}