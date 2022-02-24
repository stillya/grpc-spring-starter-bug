package authservice.repository

import dataflow.commons.dtos.abac.AbacRule
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

interface AbacRepository : R2dbcRepository<AbacRule, String> {
    fun findAbacRuleByName(name: String): Mono<AbacRule>
}