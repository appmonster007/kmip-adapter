package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.IVCounterNonce;

/**
 * TTLV deserializer for {@link IVCounterNonce}.
 */
public class IVCounterNonceTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<IVCounterNonce, IVCounterNonce.IVCounterNonceBuilder> {

  /**
   * Constructs a new {@link IVCounterNonceTtlvDeserializer}.
   */
  public IVCounterNonceTtlvDeserializer() {
    super(IVCounterNonce.kmipTag, IVCounterNonce.encodingType);
  }

  @Override
  protected IVCounterNonce.IVCounterNonceBuilder createBuilder() {
    return IVCounterNonce.builder();
  }

  @Override
  protected void setValue(IVCounterNonce.IVCounterNonceBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected IVCounterNonce build(IVCounterNonce.IVCounterNonceBuilder builder) {
    return builder.build();
  }
}
