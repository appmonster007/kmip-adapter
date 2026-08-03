package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.J;

/**
 * TTLV deserializer for {@link J}.
 */
public class JTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<J, J.JBuilder> {

  /**
   * Constructs a new {@link JTtlvDeserializer}.
   */
  public JTtlvDeserializer() {
    super(J.kmipTag, J.encodingType);
  }

  @Override
  protected J.JBuilder createBuilder() {
    return J.builder();
  }

  @Override
  protected void setValue(J.JBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected J build(J.JBuilder builder) {
    return builder.build();
  }
}
