package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.InteropOpResponsePayload;

public class InteropOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<InteropOpResponsePayload,
        InteropOpResponsePayload.InteropOpResponsePayloadBuilder> {

  public InteropOpResponsePayloadJsonDeserializer() {
    super(InteropOpResponsePayload.kmipTag, InteropOpResponsePayload.encodingType);
  }

  @Override
  protected InteropOpResponsePayload.InteropOpResponsePayloadBuilder createBuilder() {
    return InteropOpResponsePayload.builder();
  }

  @Override
  protected void setValue(InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected InteropOpResponsePayload build(
      InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}