package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SymmetricKey;

/**
 * TTLV deserializer for {@link SymmetricKey}.
 */
public class SymmetricKeyTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<SymmetricKey, SymmetricKey.SymmetricKeyBuilder> {

  /**
   * Constructs a new {@link SymmetricKeyTtlvDeserializer}.
   */
  public SymmetricKeyTtlvDeserializer() {
    super(SymmetricKey.kmipTag, SymmetricKey.encodingType);
  }

  @Override
  protected SymmetricKey.SymmetricKeyBuilder createBuilder() {
    return SymmetricKey.builder();
  }

  @Override
  protected void setValue(SymmetricKey.SymmetricKeyBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SymmetricKey build(SymmetricKey.SymmetricKeyBuilder builder) {
    return builder.build();
  }
}