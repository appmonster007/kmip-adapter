package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Q;

public class QTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Q, Q.QBuilder> {

  public QTtlvDeserializer() {
    super(Q.kmipTag, Q.encodingType);
  }

  @Override
  protected Q.QBuilder createBuilder() {
    return Q.builder();
  }

  @Override
  protected void setValue(Q.QBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected Q build(Q.QBuilder builder) {
    return builder.build();
  }
}
