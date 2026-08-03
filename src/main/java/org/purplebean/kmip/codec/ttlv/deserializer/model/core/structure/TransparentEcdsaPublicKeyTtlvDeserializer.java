package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdsaPublicKey;
import org.purplebean.kmip.model.core.type.QString;

/**
 * TTLV deserializer for {@link TransparentEcdsaPublicKey}.
 */
public class TransparentEcdsaPublicKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentEcdsaPublicKey,
        TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder> {

  /**
   * Constructs a new {@link TransparentEcdsaPublicKeyTtlvDeserializer}.
   */
  public TransparentEcdsaPublicKeyTtlvDeserializer() {
    super(TransparentEcdsaPublicKey.kmipTag, TransparentEcdsaPublicKey.encodingType);
  }

  @Override
  protected TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder createBuilder() {
    return TransparentEcdsaPublicKey.builder();
  }

  @Override
  protected void setValue(TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder,
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
  protected TransparentEcdsaPublicKey build(
      TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder) {
    return builder.build();
  }
}