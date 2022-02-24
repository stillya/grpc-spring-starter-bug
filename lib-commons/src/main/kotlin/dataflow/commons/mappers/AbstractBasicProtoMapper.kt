package dataflow.commons.mappers

import com.google.protobuf.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

abstract class AbstractBasicProtoMapper {

    //
    // String <-> UUID
    //

    fun map(value: UUID): String? {
        return value.toString()
    }

    fun map(value: String?): UUID? {
        return UUID.fromString(value)
    }

    //
    // com.google.protobuf.Timestamp <-> LocalDateTime
    //

    fun map(value: LocalDateTime): Timestamp? {
        return Timestamp.newBuilder()
            .setSeconds(value.toEpochSecond(ZoneOffset.UTC))
            .build()
    }

    fun map(value: Timestamp): LocalDateTime? {
        return LocalDateTime.ofEpochSecond(value.seconds, 0, ZoneOffset.UTC)
    }

}