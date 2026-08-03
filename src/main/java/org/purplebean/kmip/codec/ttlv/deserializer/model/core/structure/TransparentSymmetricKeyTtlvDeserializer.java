package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.TransparentSymmetricKey;
import org.purplebean.kmip.model.core.type.Key;

public class TransparentSymmetricKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentSymmetricKey,
        TransparentSymmetricKey.TransparentSymmetricKeyBuilder> {

  public TransparentSymmetricKeyTtlvDeserializer() {
    super(TransparentSymmetricKey.kmipTag, TransparentSymmetricKey.encodingType);
  }

  @Override
  protected TransparentSymmetricKey.TransparentSymmetricKeyBuilder createBuilder() {
    return TransparentSymmetricKey.builder();
  }

  @Override
  protected void setValue(TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY -> builder.key(mapper.readValue(p, Key.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentSymmetricKey build(
      TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder) {
    return builder.build();
  }
}