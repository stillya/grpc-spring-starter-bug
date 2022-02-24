package configconstructor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = ["configconstructor", "dataflow.commons"])
class ConfigConstructorApplication

fun main(args: Array<String>) {
    runApplication<ConfigConstructorApplication>(*args)
}