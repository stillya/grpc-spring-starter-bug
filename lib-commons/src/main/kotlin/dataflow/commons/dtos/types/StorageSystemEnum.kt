package dataflow.commons.dtos.types

enum class StorageSystemEnum(val value: String, val storageSystemKind: String) {
    SS_DATABASE("DATABASE", "STORAGE_SYSTEM_DATABASE"),
    SS_NFS("NFS", "STORAGE_SYSTEM_NFS"),
    SS_S3("S3", "STORAGE_SYSTEM_S3"),
    SS_KAFKA("KAFKA", "STORAGE_SYSTEM_KAFKA"),
}