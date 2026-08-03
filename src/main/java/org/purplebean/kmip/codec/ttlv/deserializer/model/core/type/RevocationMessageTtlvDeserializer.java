package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.RevocationMessage;

public class RevocationMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RevocationMessage,
        RevocationMessage.RevocationMessageBuilder> {

  public RevocationMessageTtlvDeserializer() {
    super(RevocationMessage.kmipTag, RevocationMessage.encodingType);
  }

  @Override
  protected RevocationMessage.RevocationMessageBuilder createBuilder() {
    return RevocationMessage.builder();
  }

  @Override
  protected void setValue(RevocationMessage.RevocationMessageBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected RevocationMessage build(RevocationMessage.RevocationMessageBuilder builder) {
    return builder.build();
  }
}
