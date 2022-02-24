package com.dataflow.mappers;

import com.google.protobuf.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Deprecated // Use kotlin version in this lib
public interface AbstractBasicProtoMapper {

  //
  // String <-> UUID
  //

  default String map(UUID value) {
    return value.toString();
  }

  default UUID map(String value) {
    return UUID.fromString(value);
  }

  //
  // com.google.protobuf.Timestamp <-> LocalDateTime
  //

  default Timestamp map(LocalDateTime value) {
    return Timestamp.newBuilder()
        .setSeconds(value.toEpochSecond(ZoneOffset.UTC))
        .build();
  }

  default LocalDateTime map(Timestamp value) {
    return LocalDateTime.ofEpochSecond(value.getSeconds(), 0, ZoneOffset.UTC);
  }

}
