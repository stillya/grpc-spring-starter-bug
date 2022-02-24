package configconstructor.repositories

import org.jooq.DSLContext
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles

@TestConfiguration
@ActiveProfiles(AbstractJooqTest.PROFILE)
class TestDatabaseConfig {

    @Bean
    fun configRepository(dsl: DSLContext) = ConfigRepository(dsl)

}
