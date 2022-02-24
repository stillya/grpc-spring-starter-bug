package dataflow.commons.components.fs

interface ArchiverFactory {

    fun getArchiver(beanName: String): Archiver

}