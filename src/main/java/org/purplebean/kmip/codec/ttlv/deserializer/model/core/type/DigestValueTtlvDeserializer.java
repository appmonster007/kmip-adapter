package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DigestValue;

/**
 * TTLV deserializer for {@link DigestValue}.
 */
public class DigestValueTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<DigestValue, DigestValue.DigestValueBuilder> {

  /**
   * Constructs a new {@link DigestValueTtlvDeserializer}.
   */
  public DigestValueTtlvDeserializer() {
    super(DigestValue.kmipTag, DigestValue.encodingType);
  }

  @Override
  protected DigestValue.DigestValueBuilder createBuilder() {
    return DigestValue.builder();
  }

  @Override
  protected void setValue(DigestValue.DigestValueBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected DigestValue build(DigestValue.DigestValueBuilder builder) {
    return builder.build();
  }
}
