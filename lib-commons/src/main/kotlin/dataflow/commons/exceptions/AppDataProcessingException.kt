package dataflow.commons.exceptions

import com.dataflow.commons.ErrorGroupEnum
import io.grpc.Status
import java.nio.file.Path
import java.util.*

/**
 * Exceptions caused by incorrect data format
 */
abstract class AppDataProcessingException : AppGenericException() {

    class AppInvalidManifestException(val name: String, e: Throwable) :
        AppDataProcessingException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

    class AppManifestExecutionSystemNotSpecifiedException(val businessModuleId: UUID) :
        AppDataProcessingException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

    class AppManifestExecutionSystemNotFilledException(val businessModuleId: UUID) :
        AppDataProcessingException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

    class AppBusinessModuleFileMissingException(val businessModuleId: UUID, val file: Path) :
        AppDataProcessingException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

    class AppManifestCommandNotExistsException(val name: String) : AppDataProcessingException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

    class AppManifestCommandNotApplicableHereException(val name: String, val status: String) :
        AppDataProcessingException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

    class AppManifestHookException(val name: String, e: Throwable) : AppDataProcessingException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

    class AppArchiveProcessingException(val path: String, e: Throwable) :
        AppDataProcessingException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

    class AppFileNotExistsException(val path: String) : AppDataProcessingException() {
        override val grpcCode: ErrorGroupEnum = ErrorGroupEnum.EG_INCORRECT_PARAMS
        override val grpcStatus: Status = Status.INVALID_ARGUMENT
    }

}