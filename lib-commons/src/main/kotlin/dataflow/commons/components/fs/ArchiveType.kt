package dataflow.commons.components.fs

import dataflow.commons.exceptions.AppClientException

enum class ArchiveType(val beanName: String, val extension: String) {
    TARZG("tarGzArchiver", "gz"),
    ZIP("zipArchiver", "zip"),
    ;

    companion object {
        fun getTypeByExtension(ext: String?): ArchiveType =
            values().firstOrNull { it.extension == ext }
                ?: throw AppClientException.AppIncorrectParamsException(
                    ext!!,
                    "Unsupported archive extension"
                )
    }
}
