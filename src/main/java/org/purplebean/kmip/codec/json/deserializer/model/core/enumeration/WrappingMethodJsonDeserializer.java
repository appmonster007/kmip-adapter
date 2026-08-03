package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;

/**
 * JSON deserializer for {@link WrappingMethod}.
 */
public class WrappingMethodJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<WrappingMethod, WrappingMethod.WrappingMethodBuilder> {

  /**
   * Constructs a new {@link WrappingMethodJsonDeserializer}.
   */
  public WrappingMethodJsonDeserializer() {
    super(WrappingMethod.kmipTag, WrappingMethod.encodingType);
  }

  @Override
  protected WrappingMethod.WrappingMethodBuilder createBuilder() {
    return WrappingMethod.builder();
  }

  @Override
  protected void setValue(WrappingMethod.WrappingMethodBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(WrappingMethod.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected WrappingMethod build(WrappingMethod.WrappingMethodBuilder builder) {
    return builder.build();
  }
}
