package dataflow.commons.security

import com.dataflow.commons.AuthorizationServiceGetRuleByNameRequest
import com.dataflow.commons.AuthorizationServiceGrpc
import dataflow.commons.dtos.abac.AbacRule
import dataflow.commons.mappers.toDto
import org.springframework.stereotype.Component

@Component
class AuthorizationGrpcClient(val authStub: AuthorizationServiceGrpc.AuthorizationServiceBlockingStub) {

    fun getRuleByName(name: String): AbacRule {
        val request: AuthorizationServiceGetRuleByNameRequest =
            AuthorizationServiceGetRuleByNameRequest.newBuilder()
                .setName(name)
                .build()
        return authStub.getRuleByName(request).rule.toDto()
    }

}