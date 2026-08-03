package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.NonceId;

/**
 * TTLV deserializer for {@link NonceId}.
 */
public class NonceIdTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<NonceId, NonceId.NonceIdBuilder> {

  /**
   * Constructs a new {@link NonceIdTtlvDeserializer}.
   */
  public NonceIdTtlvDeserializer() {
    super(NonceId.kmipTag, NonceId.encodingType);
  }

  @Override
  protected NonceId.NonceIdBuilder createBuilder() {
    return NonceId.builder();
  }

  @Override
  protected void setValue(NonceId.NonceIdBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected NonceId build(NonceId.NonceIdBuilder builder) {
    return builder.build();
  }
}
