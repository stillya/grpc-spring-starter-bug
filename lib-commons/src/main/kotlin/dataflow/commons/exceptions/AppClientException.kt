package dataflow.commons.exceptions

import com.dataflow.commons.ErrorGroupEnum
import io.grpc.Status

/**
 * Exceptions caused by user stupidity
 */
abstract class AppClientException : AppGenericException() {

    class AppIncorrectParamsException(val value: String, val className: String) :
        AppClientException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

    class AppAlreadyExistsException(val msg: String) : AppClientException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.ALREADY_EXISTS
    }

    class AppPreconditionFailedException(val msg: String) : AppClientException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_ENTITY_IN_ANOTHER_STAGE
        override val grpcStatus: Status = Status.FAILED_PRECONDITION
    }

    class AppPermissionDeniedException : AppClientException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_FORBIDDEN
        override val grpcStatus: Status = Status.PERMISSION_DENIED
    }

    class AppUnauthenticatedException(
        override val cause: Throwable?,
        override val message: String? = ""
    ) : AppClientException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_NOT_AUTHORIZED
        override val grpcStatus: Status = Status.UNAUTHENTICATED
    }

}