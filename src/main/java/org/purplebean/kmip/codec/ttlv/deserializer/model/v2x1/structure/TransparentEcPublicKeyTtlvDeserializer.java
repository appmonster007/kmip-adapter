package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.QString;
import org.purplebean.kmip.model.v2x1.structure.TransparentEcPublicKey;

/**
 * TTLV deserializer for {@link TransparentEcPublicKey}.
 */
public class TransparentEcPublicKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentEcPublicKey,
        TransparentEcPublicKey.TransparentEcPublicKeyBuilder> {

  /**
   * Constructs a new {@link TransparentEcPublicKeyTtlvDeserializer}.
   */
  public TransparentEcPublicKeyTtlvDeserializer() {
    super(TransparentEcPublicKey.kmipTag, TransparentEcPublicKey.encodingType);
  }

  @Override
  protected TransparentEcPublicKey.TransparentEcPublicKeyBuilder createBuilder() {
    return TransparentEcPublicKey.builder();
  }

  @Override
  protected void setValue(TransparentEcPublicKey.TransparentEcPublicKeyBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag == RecommendedCurve.kmipTag.getValue()) {
      builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
    } else if (nodeTag == QString.kmipTag.getValue()) {
      builder.qString(mapper.readValue(p, QString.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentEcPublicKey build(
      TransparentEcPublicKey.TransparentEcPublicKeyBuilder builder) {
    return builder.build();
  }
}