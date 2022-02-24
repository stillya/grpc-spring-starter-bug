package dataflow.commons.exceptions

import com.dataflow.commons.ErrorGroupEnum
import com.dataflow.commons.ErrorSourceEnum
import io.grpc.Status

/**
 * External service client exceptions solving by devops
 */
abstract class AppInfrastructureException : AppGenericException() {

    class AppServiceUnavailableException(val service: ErrorSourceEnum, val e: Throwable) :
        AppInfrastructureException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_SERVICE_UNAVAILABLE
        override val grpcStatus: Status = Status.UNAVAILABLE
    }

    class AppDatabaseUnavailableException(override val cause: Throwable?) :
        AppInfrastructureException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_DATABASE_UNAVAILABLE
        override val grpcStatus: Status = Status.UNAVAILABLE
    }

    class AppBusUnavailableException : AppInfrastructureException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_BUS_UNAVAILABLE
        override val grpcStatus: Status = Status.UNAVAILABLE
    }

    class AppExternalSystemUnavailableException(override val cause: Throwable) :
        AppInfrastructureException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_EXTERNAL_SYSTEM_UNAVAILABLE
        override val grpcStatus: Status = Status.UNAVAILABLE
    }

    class AppDatabaseLimitExceededException : AppInfrastructureException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_DATABASE_LIMIT_EXCEEDED
        override val grpcStatus: Status = Status.RESOURCE_EXHAUSTED
    }

    class AppServiceMemoryExceededException : AppInfrastructureException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_SERVICE_MEMORY_EXCEEDED
        override val grpcStatus: Status = Status.RESOURCE_EXHAUSTED
    }

}