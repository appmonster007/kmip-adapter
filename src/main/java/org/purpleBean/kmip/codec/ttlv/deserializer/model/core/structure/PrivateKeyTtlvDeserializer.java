package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PrivateKey;

public class PrivateKeyTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<PrivateKey, PrivateKey.PrivateKeyBuilder> {

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