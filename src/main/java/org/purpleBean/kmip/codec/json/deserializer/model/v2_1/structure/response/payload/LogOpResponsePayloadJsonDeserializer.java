package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LogOpResponsePayload;

public class LogOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<LogOpResponsePayload,
        LogOpResponsePayload.LogOpResponsePayloadBuilder> {

  public LogOpResponsePayloadJsonDeserializer() {
    super(LogOpResponsePayload.kmipTag, LogOpResponsePayload.encodingType);
  }

  @Override
  protected LogOpResponsePayload.LogOpResponsePayloadBuilder createBuilder() {
    return LogOpResponsePayload.builder();
  }

  @Override
  protected void setValue(LogOpResponsePayload.LogOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected LogOpResponsePayload build(LogOpResponsePayload.LogOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}