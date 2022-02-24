package dataflow.libconfigconstructor

import com.dataflow.commons.ErrorSourceEnum
import com.dataflow.libconfigconstructor.ConfigServiceFindByIdRequest
import com.dataflow.libconfigconstructor.ConfigServiceFindByTypeAndKindRequest
import com.dataflow.libconfigconstructor.ConfigServiceFindByTypeRequest
import com.dataflow.libconfigconstructor.ConfigServiceGrpc
import com.dataflow.libconfigconstructor.dto.ConfigTypeGrpcEnum
import dataflow.commons.exceptions.AppClientException
import dataflow.commons.exceptions.AppInfrastructureException
import dataflow.libconfigconstructor.dto.ConfigDto
import dataflow.libconfigconstructor.dto.toDto
import io.grpc.ManagedChannel
import org.springframework.stereotype.Component
import java.util.*

@Component
class ConfigServiceClient(val channel: ManagedChannel) {

    private val serviceName = ErrorSourceEnum.ES_CONFIG_CONSTRUCTOR

    //
    // Read
    //

    fun getConfigById(configId: UUID): ConfigDto {
        return try {
            val stub = ConfigServiceGrpc.newBlockingStub(channel)
            val request =
                ConfigServiceFindByIdRequest.newBuilder().setConfigId(configId.toString())
                    .build()
            val response = stub.getConfigById(request)
            response.dto.let { it.toDto() }
        } catch (e: Exception) {
            throw AppInfrastructureException.AppServiceUnavailableException(
                serviceName,
                e
            )
        }
    }

    fun getAllExternalSystems(): Collection<ConfigDto> {
        return try {
            val stub = ConfigServiceGrpc.newBlockingStub(channel)
            val request =
                ConfigServiceFindByTypeRequest.newBuilder()
                    .setType(ConfigTypeGrpcEnum.CT_EXTERNAL_SYSTEM)
                    .build()
            val response = stub.getAllConfigByType(request)
            response.dtoList.map { it.toDto() }
        } catch (e: Exception) {
            throw AppInfrastructureException.AppServiceUnavailableException(
                serviceName,
                e
            )
        }
    }

    fun getAllExternalSystemsByKind(kind: String): Collection<ConfigDto> {
        return try {
            val stub = ConfigServiceGrpc.newBlockingStub(channel)
            val request =
                ConfigServiceFindByTypeAndKindRequest.newBuilder()
                    .setType(ConfigTypeGrpcEnum.CT_EXTERNAL_SYSTEM).setKind(kind)
                    .build()
            val response = stub.getAllConfigByTypeAndKind(request)
            response.dtoList.map { it.toDto() }
        } catch (e: Exception) {
            throw AppInfrastructureException.AppServiceUnavailableException(
                serviceName,
                e
            )
        }
    }

    fun getExternalSystemByKind(kind: String): ConfigDto {
        return try {
            val stub = ConfigServiceGrpc.newBlockingStub(channel)
            val request =
                ConfigServiceFindByTypeAndKindRequest.newBuilder()
                    .setType(ConfigTypeGrpcEnum.CT_EXTERNAL_SYSTEM).setKind(kind)
                    .build()
            val response = stub.getAllConfigByTypeAndKind(request)
            val configs = response.dtoList.map { it.toDto() }
            if (configs.isEmpty()) {
                throw AppClientException.AppPreconditionFailedException("there is no configs with kind $kind")
            }
            if (configs.size > 1) {
                throw AppClientException.AppAlreadyExistsException("system config with kind $kind")
            }
            return configs[0]
        } catch (e: Exception) {
            throw AppInfrastructureException.AppServiceUnavailableException(
                serviceName,
                e
            )
        }
    }

}