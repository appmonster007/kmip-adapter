package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PrivateExponent;

/**
 * TTLV deserializer for {@link PrivateExponent}.
 */
public class PrivateExponentTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PrivateExponent, PrivateExponent.PrivateExponentBuilder> {

  /**
   * Constructs a new {@link PrivateExponentTtlvDeserializer}.
   */
  public PrivateExponentTtlvDeserializer() {
    super(PrivateExponent.kmipTag, PrivateExponent.encodingType);
  }

  @Override
  protected PrivateExponent.PrivateExponentBuilder createBuilder() {
    return PrivateExponent.builder();
  }

  @Override
  protected void setValue(PrivateExponent.PrivateExponentBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected PrivateExponent build(PrivateExponent.PrivateExponentBuilder builder) {
    return builder.build();
  }
}