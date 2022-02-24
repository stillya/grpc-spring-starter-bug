package authservice.controllers.grpc

import authservice.services.AuthorizationService
import com.dataflow.commons.AuthorizationServiceGetRuleByNameRequest
import com.dataflow.commons.AuthorizationServiceGetRuleByNameResponse
import com.dataflow.commons.AuthorizationServiceGrpcKt
import dataflow.commons.mappers.toProto
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class AuthorizationGrpcService(private val authorizationService: AuthorizationService) :
    AuthorizationServiceGrpcKt.AuthorizationServiceCoroutineImplBase() {

    override suspend fun getRuleByName(request: AuthorizationServiceGetRuleByNameRequest): AuthorizationServiceGetRuleByNameResponse {

        return AuthorizationServiceGetRuleByNameResponse.newBuilder()
            .setRule(authorizationService.getRuleByName(request.name)?.toProto()).build()
    }
}