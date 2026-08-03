package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.NonceValue;

public class NonceValueTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<NonceValue, NonceValue.NonceValueBuilder> {

  public NonceValueTtlvDeserializer() {
    super(NonceValue.kmipTag, NonceValue.encodingType);
  }

  @Override
  protected NonceValue.NonceValueBuilder createBuilder() {
    return NonceValue.builder();
  }

  @Override
  protected void setValue(NonceValue.NonceValueBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected NonceValue build(NonceValue.NonceValueBuilder builder) {
    return builder.build();
  }
}
