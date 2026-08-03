package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.D;

/**
 * TTLV deserializer for {@link D}.
 */
public class DTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<D, D.DBuilder> {

  /**
   * Constructs a new {@link DTtlvDeserializer}.
   */
  public DTtlvDeserializer() {
    super(D.kmipTag, D.encodingType);
  }

  @Override
  protected D.DBuilder createBuilder() {
    return D.builder();
  }

  @Override
  protected void setValue(D.DBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, BigInteger.class));
  }

  @Override
  protected D build(D.DBuilder builder) {
    return builder.build();
  }
}
