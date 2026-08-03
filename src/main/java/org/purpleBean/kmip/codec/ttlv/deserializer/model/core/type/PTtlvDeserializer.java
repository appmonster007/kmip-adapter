package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.P;

public class PTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<P, P.PBuilder> {

  public PTtlvDeserializer() {
    super(P.kmipTag, P.encodingType);
  }

  @Override
  protected P.PBuilder createBuilder() {
    return P.builder();
  }

  @Override
  protected void setValue(P.PBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected P build(P.PBuilder builder) {
    return builder.build();
  }
}
