package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Modulus;

public class ModulusTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Modulus, Modulus.ModulusBuilder> {

  public ModulusTtlvDeserializer() {
    super(Modulus.kmipTag, Modulus.encodingType);
  }

  @Override
  protected Modulus.ModulusBuilder createBuilder() {
    return Modulus.builder();
  }

  @Override
  protected void setValue(Modulus.ModulusBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected Modulus build(Modulus.ModulusBuilder builder) {
    return builder.build();
  }
}
