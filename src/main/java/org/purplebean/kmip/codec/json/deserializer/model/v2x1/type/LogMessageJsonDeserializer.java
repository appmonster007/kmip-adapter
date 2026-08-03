package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.LogMessage;

/**
 * JSON deserializer for {@link LogMessage}.
 */
public class LogMessageJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<LogMessage, LogMessage.LogMessageBuilder> {

  /**
   * Constructs a new {@link LogMessageJsonDeserializer}.
   */
  public LogMessageJsonDeserializer() {
    super(LogMessage.kmipTag, LogMessage.encodingType);
  }

  @Override
  protected LogMessage.LogMessageBuilder createBuilder() {
    return LogMessage.builder();
  }

  @Override
  protected void setValue(LogMessage.LogMessageBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected LogMessage build(LogMessage.LogMessageBuilder builder) {
    return builder.build();
  }
}
