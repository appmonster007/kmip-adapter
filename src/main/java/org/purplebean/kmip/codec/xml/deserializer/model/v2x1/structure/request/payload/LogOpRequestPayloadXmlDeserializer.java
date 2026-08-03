package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LogOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.LogMessage;

/**
 * XML deserializer for {@link LogOpRequestPayload}.
 */
public class LogOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<LogOpRequestPayload,
        LogOpRequestPayload.LogOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link LogOpRequestPayloadXmlDeserializer}.
   */
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