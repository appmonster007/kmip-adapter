package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.enumeration.InteropFunction;

public class InteropFunctionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<InteropFunction, InteropFunction.InteropFunctionBuilder> {

  public InteropFunctionJsonDeserializer() {
    super(InteropFunction.kmipTag, InteropFunction.encodingType);
  }

  @Override
  protected InteropFunction.InteropFunctionBuilder createBuilder() {
    return InteropFunction.builder();
  }

  @Override
  protected void setValue(InteropFunction.InteropFunctionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(InteropFunction.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected InteropFunction build(InteropFunction.InteropFunctionBuilder builder) {
    return builder.build();
  }
}
