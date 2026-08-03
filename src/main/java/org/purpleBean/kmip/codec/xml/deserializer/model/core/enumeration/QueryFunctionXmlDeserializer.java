package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;

public class QueryFunctionXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<QueryFunction, QueryFunction.QueryFunctionBuilder> {

  public QueryFunctionXmlDeserializer() {
    super(QueryFunction.kmipTag, QueryFunction.encodingType);
  }

  @Override
  protected QueryFunction.QueryFunctionBuilder createBuilder() {
    return QueryFunction.builder();
  }

  @Override
  protected void setValue(QueryFunction.QueryFunctionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(QueryFunction.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected QueryFunction build(QueryFunction.QueryFunctionBuilder builder) {
    return builder.build();
  }
}