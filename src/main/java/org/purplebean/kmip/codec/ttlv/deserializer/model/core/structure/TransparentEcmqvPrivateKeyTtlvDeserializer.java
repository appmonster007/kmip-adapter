package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcmqvPrivateKey;
import org.purplebean.kmip.model.core.type.D;

/**
 * TTLV deserializer for {@link TransparentEcmqvPrivateKey}.
 */
public class TransparentEcmqvPrivateKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentEcmqvPrivateKey,
        TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder> {

  /**
   * Constructs a new {@link TransparentEcmqvPrivateKeyTtlvDeserializer}.
   */
  public TransparentEcmqvPrivateKeyTtlvDeserializer() {
    super(TransparentEcmqvPrivateKey.kmipTag, TransparentEcmqvPrivateKey.encodingType);
  }

  @Override
  protected TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder createBuilder() {
    return TransparentEcmqvPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder builder,
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
  protected TransparentEcmqvPrivateKey build(
      TransparentEcmqvPrivateKey.TransparentEcmqvPrivateKeyBuilder builder) {
    return builder.build();
  }
}