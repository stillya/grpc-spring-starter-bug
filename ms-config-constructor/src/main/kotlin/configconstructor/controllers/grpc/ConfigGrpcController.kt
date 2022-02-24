package configconstructor.controllers.grpc

import com.dataflow.commons.VoidGrpcResponse
import com.dataflow.libconfigconstructor.*
import configconstructor.services.facades.ConfigService
import dataflow.libconfigconstructor.dto.toCreateDto
import dataflow.libconfigconstructor.dto.toProto
import dataflow.libconfigconstructor.dto.toUpdateDto
import dataflow.libconfigconstructor.dto.types.toDtoEnum
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import java.util.*

@GrpcService
class ConfigGrpcController(val configService: ConfigService) :
    ConfigServiceGrpc.ConfigServiceImplBase() {

    //
    // Read
    //

    override fun getConfigById(
        request: ConfigServiceFindByIdRequest,
        responseObserver: StreamObserver<ConfigServiceOneItemResponse>
    ) {
        responseObserver.onNext(configServiceOneItemResponse {
            dto = configService.getById(UUID.fromString(request.configId)).toProto()
        })
        responseObserver.onCompleted()
    }

    override fun getAllConfigByTypeAndKind(
        request: ConfigServiceFindByTypeAndKindRequest,
        responseObserver: StreamObserver<ConfigServiceManyItemsResponse>
    ) {
        responseObserver.onNext(configServiceManyItemsResponse {
            dto.addAll(
                configService.getAllConfigByTypeAndKind(request.type.toDtoEnum(), request.kind)
                    .map { it.toProto() })
        })
        responseObserver.onCompleted()
    }

    override fun getAllConfigByType(
        request: ConfigServiceFindByTypeRequest,
        responseObserver: StreamObserver<ConfigServiceManyItemsResponse>
    ) {
        responseObserver.onNext(configServiceManyItemsResponse {
            dto.addAll(
                configService.getAllConfigByType(request.type.toDtoEnum())
                    .map { it.toProto() })
        })
        responseObserver.onCompleted()
    }

    //
    // Create
    //

    override fun createConfig(
        request: ConfigServiceCreateRequest,
        responseObserver: StreamObserver<ConfigServiceOneItemResponse>
    ) {
        responseObserver.onNext(configServiceOneItemResponse {
            dto = configService.create(
                request.dto.toCreateDto(),
                UUID.fromString(request.userId)
            ).toProto()
        })
        responseObserver.onCompleted()
    }

    //
    // Update
    //

    override fun updateConfigById(
        request: ConfigServiceUpdateRequest,
        responseObserver: StreamObserver<ConfigServiceOneItemResponse>
    ) {
        responseObserver.onNext(configServiceOneItemResponse {
            dto = configService.updateById(
                request.dto.toUpdateDto(),
                UUID.fromString(request.userId)
            ).toProto()
        })
        responseObserver.onCompleted()
    }

    //
    // Delete
    //

    override fun deleteConfigById(
        request: ConfigServiceFindByIdRequest,
        responseObserver: StreamObserver<VoidGrpcResponse>
    ) {
        configService.deleteById(
            UUID.fromString(request.configId)
        )
        responseObserver.onCompleted()
    }

}