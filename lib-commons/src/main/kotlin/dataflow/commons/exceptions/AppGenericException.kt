package dataflow.commons.exceptions

import com.dataflow.commons.ErrorGroupEnum
import io.grpc.Status
import java.util.*

/**
 * Exception that could be translated to grpc status
 */
abstract class AppGenericException : RuntimeException() {
    abstract val grpcCode: ErrorGroupEnum
    abstract val grpcStatus: Status
    val description: String = "smth happend"
    val errorId = UUID.randomUUID()!!
}