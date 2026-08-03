package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.Q;

/**
 * TTLV deserializer for {@link Q}.
 */
public class QTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Q, Q.QBuilder> {

  /**
   * Constructs a new {@link QTtlvDeserializer}.
   */
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
