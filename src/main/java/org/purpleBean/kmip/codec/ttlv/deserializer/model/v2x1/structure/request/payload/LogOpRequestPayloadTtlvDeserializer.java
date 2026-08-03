package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.LogOpRequestPayload;
import org.purpleBean.kmip.model.v2x1.type.LogMessage;

public class LogOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LogOpRequestPayload,
        LogOpRequestPayload.LogOpRequestPayloadBuilder> {

  public LogOpRequestPayloadTtlvDeserializer() {
    super(LogOpRequestPayload.kmipTag, LogOpRequestPayload.encodingType);
  }

  @Override
  protected LogOpRequestPayload.LogOpRequestPayloadBuilder createBuilder() {
    return LogOpRequestPayload.builder();
  }

  @Override
  protected void setValue(LogOpRequestPayload.LogOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LOG_MESSAGE ->
          builder.logMessage(mapper.readValue(p, LogMessage.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LogOpRequestPayload build(LogOpRequestPayload.LogOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}