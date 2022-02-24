package dataflow.commons.utils

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun now(): LocalDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MICROS)
