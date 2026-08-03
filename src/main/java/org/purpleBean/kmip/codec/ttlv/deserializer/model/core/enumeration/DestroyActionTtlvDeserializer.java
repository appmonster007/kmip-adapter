package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.DestroyAction;

public class DestroyActionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DestroyAction, DestroyAction.DestroyActionBuilder> {

  public DestroyActionTtlvDeserializer() {
    super(DestroyAction.kmipTag, DestroyAction.encodingType);
  }

  @Override
  protected DestroyAction.DestroyActionBuilder createBuilder() {
    return DestroyAction.builder();
  }

  @Override
  protected void setValue(DestroyAction.DestroyActionBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(DestroyAction.fromValue(value));
  }

  @Override
  protected DestroyAction build(DestroyAction.DestroyActionBuilder builder) {
    return builder.build();
  }
}
