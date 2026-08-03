package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.P;

/**
 * TTLV deserializer for {@link P}.
 */
public class PTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<P, P.PBuilder> {

  /**
   * Constructs a new {@link PTtlvDeserializer}.
   */
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
