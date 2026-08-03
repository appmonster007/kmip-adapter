package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.AlwaysSensitive;

/**
 * JSON deserializer for {@link AlwaysSensitive}.
 */
public class AlwaysSensitiveJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AlwaysSensitive, AlwaysSensitive.AlwaysSensitiveBuilder> {

  /**
   * Constructs a new {@link AlwaysSensitiveJsonDeserializer}.
   */
  public AlwaysSensitiveJsonDeserializer() {
    super(AlwaysSensitive.kmipTag, AlwaysSensitive.encodingType);
  }

  @Override
  protected AlwaysSensitive.AlwaysSensitiveBuilder createBuilder() {
    return AlwaysSensitive.builder();
  }

  @Override
  protected void setValue(AlwaysSensitive.AlwaysSensitiveBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected AlwaysSensitive build(AlwaysSensitive.AlwaysSensitiveBuilder builder) {
    return builder.build();
  }
}