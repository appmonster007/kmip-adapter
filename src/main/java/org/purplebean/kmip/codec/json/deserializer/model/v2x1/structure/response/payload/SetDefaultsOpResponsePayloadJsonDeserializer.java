package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetDefaultsOpResponsePayload;

/**
 * JSON deserializer for {@link SetDefaultsOpResponsePayload}.
 */
public class SetDefaultsOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SetDefaultsOpResponsePayload,
        SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link SetDefaultsOpResponsePayloadJsonDeserializer}.
   */
  public SetDefaultsOpResponsePayloadJsonDeserializer() {
    super(SetDefaultsOpResponsePayload.kmipTag, SetDefaultsOpResponsePayload.encodingType);
  }

  @Override
  protected SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder createBuilder() {
    return SetDefaultsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected SetDefaultsOpResponsePayload build(
      SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}