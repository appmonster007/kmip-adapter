package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.SaltLength;

/**
 * TTLV deserializer for {@link SaltLength}.
 */
public class SaltLengthTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<SaltLength, SaltLength.SaltLengthBuilder> {

  /**
   * Constructs a new {@link SaltLengthTtlvDeserializer}.
   */
  public SaltLengthTtlvDeserializer() {
    super(SaltLength.kmipTag, SaltLength.encodingType);
  }

  @Override
  protected SaltLength.SaltLengthBuilder createBuilder() {
    return SaltLength.builder();
  }

  @Override
  protected void setValue(SaltLength.SaltLengthBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected SaltLength build(SaltLength.SaltLengthBuilder builder) {
    return builder.build();
  }
}
