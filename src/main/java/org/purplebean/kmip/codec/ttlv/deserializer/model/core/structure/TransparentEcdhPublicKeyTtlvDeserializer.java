package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdhPublicKey;
import org.purplebean.kmip.model.core.type.QString;

/**
 * TTLV deserializer for {@link TransparentEcdhPublicKey}.
 */
public class TransparentEcdhPublicKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentEcdhPublicKey,
        TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder> {

  /**
   * Constructs a new {@link TransparentEcdhPublicKeyTtlvDeserializer}.
   */
  public TransparentEcdhPublicKeyTtlvDeserializer() {
    super(TransparentEcdhPublicKey.kmipTag, TransparentEcdhPublicKey.encodingType);
  }

  @Override
  protected TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder createBuilder() {
    return TransparentEcdhPublicKey.builder();
  }

  @Override
  protected void setValue(TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.RECOMMENDED_CURVE ->
          builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
      case KmipTag.Standard.Q_STRING -> builder.qString(mapper.readValue(p, QString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentEcdhPublicKey build(
      TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder builder) {
    return builder.build();
  }
}