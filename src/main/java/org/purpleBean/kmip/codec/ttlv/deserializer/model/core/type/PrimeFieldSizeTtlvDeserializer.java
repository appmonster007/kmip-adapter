package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PrimeFieldSize;

public class PrimeFieldSizeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PrimeFieldSize, PrimeFieldSize.PrimeFieldSizeBuilder> {

  public PrimeFieldSizeTtlvDeserializer() {
    super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType);
  }

  @Override
  protected PrimeFieldSize.PrimeFieldSizeBuilder createBuilder() {
    return PrimeFieldSize.builder();
  }

  @Override
  protected void setValue(PrimeFieldSize.PrimeFieldSizeBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected PrimeFieldSize build(PrimeFieldSize.PrimeFieldSizeBuilder builder) {
    return builder.build();
  }
}