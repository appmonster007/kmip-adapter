package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PublicExponent;

/**
 * TTLV deserializer for {@link PublicExponent}.
 */
public class PublicExponentTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PublicExponent, PublicExponent.PublicExponentBuilder> {

  /**
   * Constructs a new {@link PublicExponentTtlvDeserializer}.
   */
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
