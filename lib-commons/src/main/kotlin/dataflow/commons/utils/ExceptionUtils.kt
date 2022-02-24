package dataflow.commons.utils

import dataflow.commons.exceptions.AppGenericException
import org.apache.commons.lang3.exception.ExceptionUtils

fun Throwable.getReadableThrowableMessage(): String =
    ExceptionUtils.getMessage(this)
        .toString() + System.lineSeparator() + ExceptionUtils.getStackTrace(this) +
            System.lineSeparator()

fun Throwable.getReadableThrowableMessage(errorId: String): String =
    "errorId=($errorId) - " + ExceptionUtils.getMessage(this)
        .toString() + System.lineSeparator() + ExceptionUtils.getStackTrace(this) +
            System.lineSeparator()

fun AppGenericException.getReadableThrowableMessage(): String =
    "errorId=(${this.errorId}), code=(${this.grpcCode.name}) - " + ExceptionUtils.getMessage(this)
        .toString() + System.lineSeparator() + ExceptionUtils.getStackTrace(this) +
            System.lineSeparator()


fun AppGenericException.makeDescription(serviceName: String): String =
    "[$serviceName] - errorId=(${this.errorId}), code=(${this.grpcCode.name}), desc=(${this.description})"
