package dataflow.commons.exceptions

import dataflow.commons.utils.getReadableThrowableMessage
import dataflow.commons.utils.makeDescription
import io.grpc.Status
import mu.KLogging
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.dao.DataAccessResourceFailureException

@GrpcAdvice
class GrpcExceptionAdvice(
    @Value("\${spring.application.name}")
    private val serviceName: String
) {

    companion object : KLogging()

    @GrpcExceptionHandler
    fun handleAppException(e: AppGenericException): Status {
        logger.error { e.getReadableThrowableMessage() }
        return e.grpcStatus.withDescription(e.makeDescription(serviceName)).withCause(e);
    }

    // TODO("MINOR CONTRACT") Spring, Database exceptions translator to AppGenericException subclasses

    @GrpcExceptionHandler
    fun handleDataAccessResourceFailureException(e: DataAccessResourceFailureException): Status {
        val customException = AppInfrastructureException.AppDatabaseUnavailableException(e)
        logger.error { customException.getReadableThrowableMessage() }

        return customException.grpcStatus.withDescription(
            customException.makeDescription(
                serviceName
            )
        ).withCause(e)
    }

    @GrpcExceptionHandler
    fun handleOtherException(e: Exception): Status {
        val customException = AppPlatformException.AppInternalServiceErrorException(e)
        logger.error { customException.getReadableThrowableMessage() }

        return customException.grpcStatus.withDescription(
            customException.makeDescription(
                serviceName
            )
        ).withCause(e)
    }

}