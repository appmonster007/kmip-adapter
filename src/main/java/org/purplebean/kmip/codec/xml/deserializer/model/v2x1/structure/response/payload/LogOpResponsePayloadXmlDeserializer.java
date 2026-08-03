package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LogOpResponsePayload;

public class LogOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<LogOpResponsePayload,
        LogOpResponsePayload.LogOpResponsePayloadBuilder> {

  public LogOpResponsePayloadXmlDeserializer() {
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