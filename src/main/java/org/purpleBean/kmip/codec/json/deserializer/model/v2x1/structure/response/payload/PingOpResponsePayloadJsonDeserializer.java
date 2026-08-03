package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.PingOpResponsePayload;

public class PingOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PingOpResponsePayload,
        PingOpResponsePayload.PingOpResponsePayloadBuilder> {

  public PingOpResponsePayloadJsonDeserializer() {
    super(PingOpResponsePayload.kmipTag, PingOpResponsePayload.encodingType);
  }

  @Override
  protected PingOpResponsePayload.PingOpResponsePayloadBuilder createBuilder() {
    return PingOpResponsePayload.builder();
  }

  @Override
  protected void setValue(PingOpResponsePayload.PingOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected PingOpResponsePayload build(
      PingOpResponsePayload.PingOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}