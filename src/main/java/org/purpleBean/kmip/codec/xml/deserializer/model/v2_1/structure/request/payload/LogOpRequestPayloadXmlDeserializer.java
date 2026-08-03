package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.LogMessage;

public class LogOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<LogOpRequestPayload,
        LogOpRequestPayload.LogOpRequestPayloadBuilder> {

  public LogOpRequestPayloadXmlDeserializer() {
    super(LogOpRequestPayload.kmipTag, LogOpRequestPayload.encodingType);
  }

  @Override
  protected LogOpRequestPayload.LogOpRequestPayloadBuilder createBuilder() {
    return LogOpRequestPayload.builder();
  }

  @Override
  protected void setValue(LogOpRequestPayload.LogOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LOG_MESSAGE -> builder.logMessage(ctxt.readValue(p, LogMessage.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LogOpRequestPayload build(LogOpRequestPayload.LogOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}