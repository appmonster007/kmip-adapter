package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.structure.response.payload.ObliterateOpResponsePayload;

/**
 * JSON deserializer for {@link ObliterateOpResponsePayload}.
 */
public class ObliterateOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ObliterateOpResponsePayload,
        ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ObliterateOpResponsePayloadJsonDeserializer}.
   */
  public ObliterateOpResponsePayloadJsonDeserializer() {
    super(ObliterateOpResponsePayload.kmipTag, ObliterateOpResponsePayload.encodingType);
  }

  @Override
  protected ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder createBuilder() {
    return ObliterateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected ObliterateOpResponsePayload build(
      ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}