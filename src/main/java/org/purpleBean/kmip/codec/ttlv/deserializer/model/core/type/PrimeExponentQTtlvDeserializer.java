package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.PrimeExponentQ;

public class PrimeExponentQTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PrimeExponentQ, PrimeExponentQ.PrimeExponentQBuilder> {

  public PrimeExponentQTtlvDeserializer() {
    super(PrimeExponentQ.kmipTag, PrimeExponentQ.encodingType);
  }

  @Override
  protected PrimeExponentQ.PrimeExponentQBuilder createBuilder() {
    return PrimeExponentQ.builder();
  }

  @Override
  protected void setValue(PrimeExponentQ.PrimeExponentQBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected PrimeExponentQ build(PrimeExponentQ.PrimeExponentQBuilder builder) {
    return builder.build();
  }
}