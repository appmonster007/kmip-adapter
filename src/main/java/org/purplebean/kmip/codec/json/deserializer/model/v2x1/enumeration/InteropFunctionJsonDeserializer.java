package org.purplebean.kmip.codec.json.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;

/**
 * JSON deserializer for {@link InteropFunction}.
 */
public class InteropFunctionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<InteropFunction, InteropFunction.InteropFunctionBuilder> {

  /**
   * Constructs a new {@link InteropFunctionJsonDeserializer}.
   */
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
