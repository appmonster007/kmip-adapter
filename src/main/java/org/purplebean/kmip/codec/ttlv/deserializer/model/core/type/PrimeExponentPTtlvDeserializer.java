package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PrimeExponentP;

/**
 * TTLV deserializer for {@link PrimeExponentP}.
 */
public class PrimeExponentPTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PrimeExponentP, PrimeExponentP.PrimeExponentPBuilder> {

  /**
   * Constructs a new {@link PrimeExponentPTtlvDeserializer}.
   */
  public PrimeExponentPTtlvDeserializer() {
    super(PrimeExponentP.kmipTag, PrimeExponentP.encodingType);
  }

  @Override
  protected PrimeExponentP.PrimeExponentPBuilder createBuilder() {
    return PrimeExponentP.builder();
  }

  @Override
  protected void setValue(PrimeExponentP.PrimeExponentPBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected PrimeExponentP build(PrimeExponentP.PrimeExponentPBuilder builder) {
    return builder.build();
  }
}