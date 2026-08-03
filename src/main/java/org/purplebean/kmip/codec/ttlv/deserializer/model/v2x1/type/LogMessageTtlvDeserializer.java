package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.LogMessage;

/**
 * TTLV deserializer for {@link LogMessage}.
 */
public class LogMessageTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<LogMessage, LogMessage.LogMessageBuilder> {

  /**
   * Constructs a new {@link LogMessageTtlvDeserializer}.
   */
  public LogMessageTtlvDeserializer() {
    super(LogMessage.kmipTag, LogMessage.encodingType);
  }

  @Override
  protected LogMessage.LogMessageBuilder createBuilder() {
    return LogMessage.builder();
  }

  @Override
  protected void setValue(LogMessage.LogMessageBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected LogMessage build(LogMessage.LogMessageBuilder builder) {
    return builder.build();
  }
}
