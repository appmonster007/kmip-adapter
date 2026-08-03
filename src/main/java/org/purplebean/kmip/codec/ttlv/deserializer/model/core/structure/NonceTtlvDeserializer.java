package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.Nonce;
import org.purplebean.kmip.model.core.type.NonceId;
import org.purplebean.kmip.model.core.type.NonceValue;

/**
 * TTLV deserializer for {@link Nonce}.
 */
public class NonceTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Nonce, Nonce.NonceBuilder> {

  /**
   * Constructs a new {@link NonceTtlvDeserializer}.
   */
  public NonceTtlvDeserializer() {
    super(Nonce.kmipTag, Nonce.encodingType);
  }

  @Override
  protected Nonce.NonceBuilder createBuilder() {
    return Nonce.builder();
  }

  @Override
  protected void setValue(Nonce.NonceBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.NONCE_ID -> builder.nonceId(mapper.readValue(p, NonceId.class));
      case KmipTag.Standard.NONCE_VALUE ->
          builder.nonceValue(mapper.readValue(p, NonceValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Nonce build(Nonce.NonceBuilder builder) {
    return builder.build();
  }
}