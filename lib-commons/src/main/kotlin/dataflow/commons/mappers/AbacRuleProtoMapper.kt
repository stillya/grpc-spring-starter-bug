package dataflow.commons.mappers

import com.dataflow.commons.AbacRuleGrpc
import dataflow.commons.dtos.abac.AbacRule
import org.mapstruct.CollectionMappingStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.factory.Mappers

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface AbacRuleProtoMapper {

    fun toProto(rule: AbacRule): AbacRuleGrpc
    fun toDto(rule: AbacRuleGrpc): AbacRule

    companion object {
        val INSTANCE: AbacRuleProtoMapper = Mappers.getMapper(AbacRuleProtoMapper::class.java)
    }

}

fun AbacRuleGrpc.toDto() =
    AbacRuleProtoMapper.INSTANCE.toDto(this)

fun AbacRule.toProto() =
    AbacRuleProtoMapper.INSTANCE.toProto(this)