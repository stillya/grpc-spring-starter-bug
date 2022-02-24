package configconstructor.repositories

import com.dataflow.configconstructor.Tables.CONFIG
import com.dataflow.configconstructor.enums.ConfigType
import com.dataflow.configconstructor.tables.daos.ConfigDao
import com.dataflow.configconstructor.tables.pojos.Config
import org.jooq.DSLContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ConfigRepository(val dsl: DSLContext) : ConfigDao(dsl.configuration()) {
    private val tbl = CONFIG

    //
    // Read
    //

    fun getAllByConfigTypeAndKind(type: ConfigType, kind: String): Collection<Config> =
        dsl.selectFrom(tbl).where(tbl.CONFIG_TYPE.eq(type).and(tbl.KIND.eq(kind))).fetch()
            .into(Config::class.java)

    fun search(pageable: Pageable, type: ConfigType, kind: String?): Page<Config> {
        val condition = tbl.CONFIG_TYPE.eq(type)
        if (kind != null) {
            condition.and(tbl.KIND.like("%${kind}%"))
        }
        // select count
        val total = dsl.selectCount().from(tbl).where(condition).fetchOptional().orElseThrow()
            .into(Long::class.java)
        // select page
        val records = dsl.selectFrom(tbl)
            .where(condition)
            .limit(pageable.pageSize)
            .offset(pageable.offset)
            .fetch().into(Config::class.java)
        return PageImpl(records, pageable, total)
    }

    //
    // Create
    //

    fun create(
        configPojo: Config
    ): Config =
        dsl.insertInto(tbl)
            .set(
                dsl.newRecord(
                    tbl, configPojo
                )
            )
            .returning().fetchOptional().orElseThrow().into(Config::class.java)

    //
    // Update
    //

    fun modify(
        configPojo: Config
    ): Config =
        dsl.update(tbl)
            .set(
                dsl.newRecord(
                    tbl, configPojo
                )
            )
            .returning().fetchOptional().orElseThrow().into(Config::class.java)

}
