package dataflow.commons.components.fs

import com.github.mustachejava.DefaultMustacheFactory
import java.io.*
import java.nio.charset.StandardCharsets

class TemplateProcessor {

    companion object {

        fun createFileFromMustacheTemplate(
            srcFile: File,
            dstFile: File,
            context: Map<String, Any>
        ) {
            InputStreamReader(FileInputStream(srcFile)).use { reader ->
                FileWriter(dstFile).use { writer ->
                    val mustache = DefaultMustacheFactory()
                        .compile(reader, srcFile.name)
                    mustache.execute(writer, context).flush()
                }
            }
        }

        fun processMustacheTemplate(
            template: String,
            name: String,
            context: Map<String, Any>
        ): String {
            InputStreamReader(template.byteInputStream(StandardCharsets.UTF_8)).use { reader ->
                val writer = StringWriter().use { writer ->
                    val mustache = DefaultMustacheFactory()
                        .compile(reader, name)
                    mustache.execute(writer, context).flush()
                }
                return writer.toString()
            }
        }

    }

}