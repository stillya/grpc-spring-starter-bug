package dataflow.commons.components.fs

import dataflow.commons.exceptions.AppDataProcessingException
import mu.KLogging
import org.apache.commons.compress.archivers.ArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream
import org.springframework.stereotype.Component
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.io.path.inputStream
import kotlin.io.path.name
import kotlin.io.path.outputStream


@Component("tarGzArchiver")
class TarGzArchiver : Archiver, KLogging() {

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
        dstFile.outputStream().buffered().use {
            GzipCompressorOutputStream(it).use { gzipStream ->
                TarArchiveOutputStream(gzipStream).use { tarArchive ->
                    addFileToTarGz(tarArchive, srcDir, "")
                }
            }
        }
    }

//
// Helpers
//

    private fun unarchive(srcStream: InputStream, dstDir: Path) {
        GzipCompressorInputStream(srcStream).use { gzStream ->
            TarArchiveInputStream(gzStream).use { archiveInputStream ->
                var entry: ArchiveEntry?
                while (archiveInputStream.nextEntry.also { entry = it } != null) {
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


    private fun addFileToTarGz(tOut: TarArchiveOutputStream, srcPath: Path, base: String) {
        val f = srcPath.toFile()
        val entryName = srcPath.name
        val tarEntry = TarArchiveEntry(f, entryName)
        tOut.putArchiveEntry(tarEntry)
        if (f.isFile) {
            f.inputStream().use {
                it.copyTo(tOut)
                tOut.closeArchiveEntry()
            }
        } else {
            tOut.closeArchiveEntry()
            f.listFiles()?.forEach {
                addFileToTarGz(tOut, it.toPath(), "$entryName/")
            }
        }
    }

}