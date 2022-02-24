package dataflow.commons.exceptions

import dataflow.commons.utils.getReadableThrowableMessage
import io.grpc.StatusRuntimeException
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.dao.DataAccessResourceFailureException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

data class ErrorResponse(val code: String, val description: String, val errorId: UUID)

@RestControllerAdvice
class RestExceptionAdvice(
    @Value("\${spring.application.name}") private val serviceName: String
) {

    companion object : KLogging()

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AppGenericException::class)
    fun handleAppException(e: AppGenericException): ErrorResponse {
        logger.error { e.getReadableThrowableMessage() }

        return ErrorResponse(e.grpcCode.name, e.description, e.errorId)
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessResourceFailureException::class)
    fun handleDataAccessResourceFailureException(e: DataAccessResourceFailureException): ErrorResponse {
        val customException = AppInfrastructureException.AppDatabaseUnavailableException(e)
        logger.error { customException.getReadableThrowableMessage() }

        return ErrorResponse(
            customException.grpcCode.name,
            customException.description,
            customException.errorId
        )
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleOtherExceptions(e: Exception): ErrorResponse {
        val customException = AppPlatformException.AppInternalServiceErrorException(e)
        logger.error { customException.getReadableThrowableMessage() }

        return ErrorResponse(customException.grpcCode.name, e.message!!, customException.errorId)
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StatusRuntimeException::class)
    fun handleGrpcClientException(e: StatusRuntimeException): ErrorResponse {
        return if (e.cause == null) {
            logger.error { e.status.description }

            val descRegex = """desc=\(([^()]*)\)""".toRegex()
            val errorRegex = """errorId=\(([^()]*)\)""".toRegex()

            val (description) = descRegex.find(e.status.description!!)!!.destructured
            val (errorId) = errorRegex.find(e.status.description!!)!!.destructured
            ErrorResponse(e.status.code.name, description, UUID.fromString(errorId))
        } else {
            val err = ErrorResponse(e.status.code.name, e.status.description!!, UUID.randomUUID())
            logger.error { e.getReadableThrowableMessage(err.errorId.toString()) }
            return err
        }
    }
}