package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.PrivateKey;

/**
 * TTLV deserializer for {@link PrivateKey}.
 */
public class PrivateKeyTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<PrivateKey, PrivateKey.PrivateKeyBuilder> {

  /**
   * Constructs a new {@link PrivateKeyTtlvDeserializer}.
   */
  public PrivateKeyTtlvDeserializer() {
    super(PrivateKey.kmipTag, PrivateKey.encodingType);
  }

  @Override
  protected PrivateKey.PrivateKeyBuilder createBuilder() {
    return PrivateKey.builder();
  }

  @Override
  protected void setValue(PrivateKey.PrivateKeyBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PrivateKey build(PrivateKey.PrivateKeyBuilder builder) {
    return builder.build();
  }
}