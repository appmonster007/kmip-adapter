package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.PublicKey;

public class PublicKeyTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<PublicKey, PublicKey.PublicKeyBuilder> {

  public PublicKeyTtlvDeserializer() {
    super(PublicKey.kmipTag, PublicKey.encodingType);
  }

  @Override
  protected PublicKey.PublicKeyBuilder createBuilder() {
    return PublicKey.builder();
  }

  @Override
  protected void setValue(PublicKey.PublicKeyBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PublicKey build(PublicKey.PublicKeyBuilder builder) {
    return builder.build();
  }
}