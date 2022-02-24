package dataflow.commons.components.fs

import org.springframework.stereotype.Service

@Service
class archive : ArchiverFactory {
    override fun getArchiver(beanName: String): Archiver {
        TODO("Not yet implemented")
    }
}