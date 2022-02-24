package dataflow.commons.components.fs

import org.springframework.stereotype.Component
import java.nio.file.Path
import kotlin.io.path.extension

@Component
class ArchiverDispatcher(val archiverFactory: ArchiverFactory) {

    fun getArchiverByFileExtension(fileName: Path): Archiver = archiverFactory.getArchiver(
        ArchiveType.getTypeByExtension(fileName.extension)
            .beanName
    )

    private fun getDefaultArchiverType(): ArchiveType = ArchiveType.TARZG

    fun getDefaultArchiver(): Archiver =
        archiverFactory.getArchiver(getDefaultArchiverType().beanName)
}