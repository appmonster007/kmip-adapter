package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.D;
import org.purplebean.kmip.model.v2x1.structure.TransparentEcPrivateKey;

/**
 * TTLV deserializer for {@link TransparentEcPrivateKey}.
 */
public class TransparentEcPrivateKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentEcPrivateKey,
        TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder> {

  /**
   * Constructs a new {@link TransparentEcPrivateKeyTtlvDeserializer}.
   */
  public TransparentEcPrivateKeyTtlvDeserializer() {
    super(TransparentEcPrivateKey.kmipTag, TransparentEcPrivateKey.encodingType);
  }

  @Override
  protected TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder createBuilder() {
    return TransparentEcPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag == RecommendedCurve.kmipTag.getValue()) {
      builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
    } else if (nodeTag == D.kmipTag.getValue()) {
      builder.d(mapper.readValue(p, D.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentEcPrivateKey build(
      TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder builder) {
    return builder.build();
  }
}