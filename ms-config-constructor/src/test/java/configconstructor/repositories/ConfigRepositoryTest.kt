package configconstructor.repositories

import com.dataflow.configconstructor.enums.ConfigType
import com.dataflow.configconstructor.tables.pojos.Config
import dataflow.commons.utils.now
import org.jooq.JSONB
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.apachecommons.ReflectionEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import java.util.*

@ContextConfiguration(classes = [TestDatabaseConfig::class])
internal class ConfigRepositoryTest : AbstractJooqTest() {

    @Autowired
    lateinit var configRepository: ConfigRepository

    @Test
    fun testCreateConfig() {
        val config = Config(
            UUID.randomUUID(),
            now(),
            now(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            ConfigType.CT_SCHEMA,
            JSONB.jsonb("{}")
        )
        val created = configRepository.create(config)
        val expected = ReflectionEquals(config);
        Assertions.assertTrue(expected.matches(created))
    }
}