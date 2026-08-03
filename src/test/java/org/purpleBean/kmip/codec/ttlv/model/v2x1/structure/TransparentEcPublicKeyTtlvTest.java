package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.QString;
import org.purplebean.kmip.model.v2x1.structure.TransparentEcPublicKey;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("TransparentEcPublicKey Ttlv Serialization Tests")
class TransparentEcPublicKeyTtlvTest
    extends AbstractTtlvSerializationTestSuite<TransparentEcPublicKey> {

  @Override
  public Class<TransparentEcPublicKey> type() {
    return TransparentEcPublicKey.class;
  }

  @Override
  public TransparentEcPublicKey createDefault() {
    return TransparentEcPublicKey
        .builder()
        .recommendedCurve(RecommendedCurve.Standard.P_192.inst())
        .qString(QString.of(new byte[] {0x01, 0x02, 0x03}))
        .build();
  }

  @Override
  public TransparentEcPublicKey createVariant() {
    return TransparentEcPublicKey
        .builder()
        .recommendedCurve(RecommendedCurve.Standard.P_224.inst())
        .qString(QString.of(new byte[] {0x04, 0x05, 0x06}))
        .build();
  }
}