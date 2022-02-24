package dataflow.commons.components.fs

import dataflow.commons.exceptions.AppDataProcessingException
import mu.KLogging
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream
import org.springframework.stereotype.Component
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.io.path.absolutePathString
import kotlin.io.path.inputStream

@Component("zipArchiver")
class ZipArchiver : Archiver, KLogging() {

    override fun unarchive(srcFile: Path, dstDir: Path) {
        try {
            srcFile.inputStream().buffered().use {
                unarchive(
                    it,
                    dstDir
                )
            }
        } catch (e: IOException) {
            throw AppDataProcessingException.AppArchiveProcessingException(srcFile.toString(), e)
        }
    }

    override fun archive(srcDir: Path, dstFile: Path) {
        ZipOutputStream(BufferedOutputStream(FileOutputStream(dstFile.toFile()))).use { zos ->
            srcDir.toFile().walkTopDown().forEach { file ->
                val zipFileName =
                    file.absolutePath.removePrefix(srcDir.absolutePathString()).removePrefix("/")
                val entry = ZipEntry("$zipFileName${(if (file.isDirectory) "/" else "")}")
                zos.putNextEntry(entry)
                if (file.isFile) {
                    file.inputStream().copyTo(zos)
                }
            }
        }
    }

    //
    // Helpers
    //

    private fun unarchive(srcStream: InputStream, dstDir: Path) {
        ZipArchiveInputStream(srcStream).use { archiveInputStream ->
            var entry: ZipArchiveEntry?
            while (archiveInputStream.nextZipEntry.also { entry = it } != null) {
                val pathEntryOutput: Path = dstDir.resolve(entry!!.name)
                if (entry!!.isDirectory) {
                    if (!Files.exists(pathEntryOutput)) {
                        Files.createDirectory(pathEntryOutput)
                    }
                } else {
                    Files.copy(
                        archiveInputStream,
                        pathEntryOutput,
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }
            }
        }
    }

}