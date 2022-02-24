package dataflow.commons.exceptions

import com.dataflow.commons.ErrorGroupEnum
import io.grpc.Status

/**
 * Internal exceptions caused due to bad code quality
 */
abstract class AppPlatformException : AppGenericException() {

    class AppDatabaseQueryFailedException : AppPlatformException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_DATABASE_QUERY_FAILED
        override val grpcStatus: Status = Status.INTERNAL
    }

    class AppInternalServiceErrorException(override val cause: Throwable?) :
        AppPlatformException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INTERNAL_SERVICE_ERROR
        override val grpcStatus: Status = Status.INTERNAL
    }

    class AppCodeUnreachableException : AppPlatformException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INTERNAL_SERVICE_ERROR
        override val grpcStatus: Status = Status.INTERNAL
    }

}