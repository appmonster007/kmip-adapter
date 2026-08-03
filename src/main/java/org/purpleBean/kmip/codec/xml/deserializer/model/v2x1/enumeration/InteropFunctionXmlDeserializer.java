package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;

public class InteropFunctionXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<InteropFunction, InteropFunction.InteropFunctionBuilder> {

  public InteropFunctionXmlDeserializer() {
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