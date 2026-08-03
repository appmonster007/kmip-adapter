package org.purplebean.kmip.codec.json.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.response.SimpleResponsePayload;

/**
 * JSON deserializer for {@link SimpleResponsePayload}.
 */
public class SimpleResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SimpleResponsePayload,
        SimpleResponsePayload.SimpleResponsePayloadBuilder> {

  /**
   * Constructs a new {@link SimpleResponsePayloadJsonDeserializer}.
   */
  public SimpleResponsePayloadJsonDeserializer() {
    super(SimpleResponsePayload.kmipTag, SimpleResponsePayload.encodingType);
  }

  @Override
  protected SimpleResponsePayload.SimpleResponsePayloadBuilder createBuilder() {
    return SimpleResponsePayload.builder();
  }

  @Override
  protected void setValue(SimpleResponsePayload.SimpleResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
// No fields to deserialize
  }

  @Override
  protected SimpleResponsePayload build(
      SimpleResponsePayload.SimpleResponsePayloadBuilder builder) {
    return builder.build();
  }
}
