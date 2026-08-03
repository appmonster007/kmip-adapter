package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.MaximumResponseSize;

/**
 * JSON deserializer for {@link MaximumResponseSize}.
 */
public class MaximumResponseSizeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MaximumResponseSize,
        MaximumResponseSize.MaximumResponseSizeBuilder> {

  /**
   * Constructs a new {@link MaximumResponseSizeJsonDeserializer}.
   */
  public MaximumResponseSizeJsonDeserializer() {
    super(MaximumResponseSize.kmipTag, MaximumResponseSize.encodingType);
  }

  @Override
  protected MaximumResponseSize.MaximumResponseSizeBuilder createBuilder() {
    return MaximumResponseSize.builder();
  }

  @Override
  protected void setValue(MaximumResponseSize.MaximumResponseSizeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected MaximumResponseSize build(MaximumResponseSize.MaximumResponseSizeBuilder builder) {
    return builder.build();
  }
}
