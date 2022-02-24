package dataflow.commons.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.jooq.JSONB

class JsonbDeserializer(vc: Class<*>? = null) : StdDeserializer<JSONB>(vc) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): JSONB {
        return JSONB.jsonb(p.codec.readTree<JsonNode>(p).toString())
    }
}