package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdhPrivateKey;
import org.purplebean.kmip.model.core.type.D;

public class TransparentEcdhPrivateKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentEcdhPrivateKey,
        TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder> {

  public TransparentEcdhPrivateKeyTtlvDeserializer() {
    super(TransparentEcdhPrivateKey.kmipTag, TransparentEcdhPrivateKey.encodingType);
  }

  @Override
  protected TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder createBuilder() {
    return TransparentEcdhPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.RECOMMENDED_CURVE ->
          builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
      case KmipTag.Standard.D -> builder.d(mapper.readValue(p, D.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentEcdhPrivateKey build(
      TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder builder) {
    return builder.build();
  }
}