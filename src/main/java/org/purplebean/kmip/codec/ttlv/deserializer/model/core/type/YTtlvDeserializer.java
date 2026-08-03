package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.Y;

/**
 * TTLV deserializer for {@link Y}.
 */
public class YTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Y, Y.YBuilder> {

  /**
   * Constructs a new {@link YTtlvDeserializer}.
   */
  public YTtlvDeserializer() {
    super(Y.kmipTag, Y.encodingType);
  }

  @Override
  protected Y.YBuilder createBuilder() {
    return Y.builder();
  }

  @Override
  protected void setValue(Y.YBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, BigInteger.class));
  }

  @Override
  protected Y build(Y.YBuilder builder) {
    return builder.build();
  }
}