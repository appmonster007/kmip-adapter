package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LogoutOpResponsePayload;

public class LogoutOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<LogoutOpResponsePayload,
        LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder> {

  public LogoutOpResponsePayloadJsonDeserializer() {
    super(LogoutOpResponsePayload.kmipTag, LogoutOpResponsePayload.encodingType);
  }

  @Override
  protected LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder createBuilder() {
    return LogoutOpResponsePayload.builder();
  }

  @Override
  protected void setValue(LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected LogoutOpResponsePayload build(
      LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}