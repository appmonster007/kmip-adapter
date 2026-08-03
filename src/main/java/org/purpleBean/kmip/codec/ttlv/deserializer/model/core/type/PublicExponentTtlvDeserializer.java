package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.PublicExponent;

public class PublicExponentTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PublicExponent, PublicExponent.PublicExponentBuilder> {

  public PublicExponentTtlvDeserializer() {
    super(PublicExponent.kmipTag, PublicExponent.encodingType);
  }

  @Override
  protected PublicExponent.PublicExponentBuilder createBuilder() {
    return PublicExponent.builder();
  }

  @Override
  protected void setValue(PublicExponent.PublicExponentBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected PublicExponent build(PublicExponent.PublicExponentBuilder builder) {
    return builder.build();
  }
}
