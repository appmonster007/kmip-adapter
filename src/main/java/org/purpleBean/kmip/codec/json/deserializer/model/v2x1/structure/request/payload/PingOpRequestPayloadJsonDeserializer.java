package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.PingOpRequestPayload;

public class PingOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PingOpRequestPayload,
        PingOpRequestPayload.PingOpRequestPayloadBuilder> {

  public PingOpRequestPayloadJsonDeserializer() {
    super(PingOpRequestPayload.kmipTag, PingOpRequestPayload.encodingType);
  }

  @Override
  protected PingOpRequestPayload.PingOpRequestPayloadBuilder createBuilder() {
    return PingOpRequestPayload.builder();
  }

  @Override
  protected void setValue(PingOpRequestPayload.PingOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected PingOpRequestPayload build(PingOpRequestPayload.PingOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}