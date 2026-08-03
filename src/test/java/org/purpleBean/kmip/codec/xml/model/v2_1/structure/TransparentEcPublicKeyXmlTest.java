package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPublicKey;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TransparentEcPublicKey Xml Serialization Tests")
class TransparentEcPublicKeyXmlTest
    extends AbstractXmlSerializationTestSuite<TransparentEcPublicKey> {

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