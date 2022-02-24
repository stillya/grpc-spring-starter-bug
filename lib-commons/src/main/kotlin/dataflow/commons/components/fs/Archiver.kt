package dataflow.commons.components.fs

import java.nio.file.Path

interface Archiver {

    /**
     * Extracts archive file srcFile to existent dstDir directory.
     */
    fun unarchive(srcFile: Path, dstDir: Path)
    fun archive(srcDir: Path, dstFile: Path)

}