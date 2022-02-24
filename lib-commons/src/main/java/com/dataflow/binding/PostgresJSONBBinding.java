package com.dataflow.binding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Types;
import java.util.Objects;

public class PostgresJSONBBinding implements Binding<Object, JsonNode> {

  private ObjectMapper mapper;

  public PostgresJSONBBinding() {
    this.mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public Converter<Object, JsonNode> converter() {
    return new Converter<>() {
      @Override
      public JsonNode from(Object t) {
        return t == null ? mapper.createObjectNode() : mapper.valueToTree(t);
      }

      @Override
      public Object to(JsonNode u) {
        try {
          return u == null ? null : mapper.writeValueAsString(u);
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }
        return u;
      }

      @Override
      public Class<Object> fromType() {
        return Object.class;
      }

      @Override
      public Class<JsonNode> toType() {
        return JsonNode.class;
      }
    };
  }

  @Override
  public void sql(BindingSQLContext<JsonNode> ctx) throws SQLException {
    ctx.render().visit(DSL.val(ctx.convert(converter()).value())).sql("::jsonb");
  }

  @Override
  public void register(final BindingRegisterContext<JsonNode> ctx) throws SQLException {
    ctx.statement().registerOutParameter(ctx.index(), Types.VARCHAR);
  }

  @Override
  public void set(final BindingSetStatementContext<JsonNode> ctx) throws SQLException {
    ctx.statement()
        .setString(ctx.index(), Objects.toString(ctx.convert(converter()).value(), null));
  }

  @Override
  public void set(final BindingSetSQLOutputContext<JsonNode> ctx) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  @Override
  public void get(final BindingGetResultSetContext<JsonNode> ctx) throws SQLException {
    ctx.convert(converter()).value(ctx.resultSet().getString(ctx.index()));
  }

  @Override
  public void get(final BindingGetStatementContext<JsonNode> ctx) throws SQLException {
    ctx.convert(converter()).value(ctx.statement().getString(ctx.index()));
  }

  @Override
  public void get(final BindingGetSQLInputContext<JsonNode> ctx) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

}