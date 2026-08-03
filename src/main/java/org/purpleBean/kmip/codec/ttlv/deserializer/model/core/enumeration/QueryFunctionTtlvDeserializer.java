package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;

public class QueryFunctionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<QueryFunction, QueryFunction.QueryFunctionBuilder> {

  public QueryFunctionTtlvDeserializer() {
    super(QueryFunction.kmipTag, QueryFunction.encodingType);
  }

  @Override
  protected QueryFunction.QueryFunctionBuilder createBuilder() {
    return QueryFunction.builder();
  }

  @Override
  protected void setValue(QueryFunction.QueryFunctionBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(QueryFunction.fromValue(value));
  }

  @Override
  protected QueryFunction build(QueryFunction.QueryFunctionBuilder builder) {
    return builder.build();
  }
}
