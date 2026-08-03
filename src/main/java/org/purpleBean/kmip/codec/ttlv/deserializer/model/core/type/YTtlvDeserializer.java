package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Y;

public class YTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Y, Y.YBuilder> {

  public YTtlvDeserializer() {
    super(Y.kmipTag, Y.encodingType);
  }

  @Override
  protected Y.YBuilder createBuilder() {
    return Y.builder();
  }

  @Override
  protected void setValue(Y.YBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, BigInteger.class));
  }

  @Override
  protected Y build(Y.YBuilder builder) {
    return builder.build();
  }
}