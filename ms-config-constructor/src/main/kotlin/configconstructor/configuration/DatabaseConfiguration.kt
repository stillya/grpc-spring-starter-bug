package configconstructor.configuration

import configconstructor.exception.ExceptionTranslator
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.jooq.impl.DefaultExecuteListenerProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource

@Configuration
class DatabaseConfiguration(val dataSource: DataSource) {
    //
    // Beans
    //

    @Bean
    fun connectionProvider(): DataSourceConnectionProvider {
        return DataSourceConnectionProvider(TransactionAwareDataSourceProxy(dataSource))
    }


    @Bean
    fun dsl(): DSLContext {
        return DefaultDSLContext(configuration())
    }

    //
    // Helpers
    //

    fun configuration(): org.jooq.Configuration {
        val jooqConfiguration = DefaultConfiguration()
        jooqConfiguration.set(connectionProvider())
        jooqConfiguration.set(SQLDialect.POSTGRES)
        jooqConfiguration.set(DefaultExecuteListenerProvider(ExceptionTranslator()))
        return jooqConfiguration
    }
}