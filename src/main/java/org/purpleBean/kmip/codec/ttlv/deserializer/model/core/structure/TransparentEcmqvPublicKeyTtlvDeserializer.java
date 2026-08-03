package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcmqvPublicKey;
import org.purpleBean.kmip.model.core.type.QString;

public class TransparentEcmqvPublicKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentEcmqvPublicKey,
        TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder> {

  public TransparentEcmqvPublicKeyTtlvDeserializer() {
    super(TransparentEcmqvPublicKey.kmipTag, TransparentEcmqvPublicKey.encodingType);
  }

  @Override
  protected TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder createBuilder() {
    return TransparentEcmqvPublicKey.builder();
  }

  @Override
  protected void setValue(TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder builder,
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
  protected TransparentEcmqvPublicKey build(
      TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder builder) {
    return builder.build();
  }
}