package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.DeactivationMessage;

public class DeactivationMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeactivationMessage,
        DeactivationMessage.DeactivationMessageBuilder> {

  public DeactivationMessageTtlvDeserializer() {
    super(DeactivationMessage.kmipTag, DeactivationMessage.encodingType);
  }

  @Override
  protected DeactivationMessage.DeactivationMessageBuilder createBuilder() {
    return DeactivationMessage.builder();
  }

  @Override
  protected void setValue(DeactivationMessage.DeactivationMessageBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected DeactivationMessage build(DeactivationMessage.DeactivationMessageBuilder builder) {
    return builder.build();
  }
}
