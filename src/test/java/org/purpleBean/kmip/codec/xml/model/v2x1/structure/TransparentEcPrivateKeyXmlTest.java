package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.D;
import org.purplebean.kmip.model.v2x1.structure.TransparentEcPrivateKey;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TransparentEcPrivateKey Xml Serialization Tests")
class TransparentEcPrivateKeyXmlTest
    extends AbstractXmlSerializationTestSuite<TransparentEcPrivateKey> {

  @Override
  public Class<TransparentEcPrivateKey> type() {
    return TransparentEcPrivateKey.class;
  }

  @Override
  public TransparentEcPrivateKey createDefault() {
    return TransparentEcPrivateKey
        .builder()
        .recommendedCurve(RecommendedCurve.Standard.P_192.inst())
        .d(D.of(BigInteger.ONE))
        .build();
  }

  @Override
  public TransparentEcPrivateKey createVariant() {
    return TransparentEcPrivateKey
        .builder()
        .recommendedCurve(RecommendedCurve.Standard.P_224.inst())
        .d(D.of(BigInteger.TEN))
        .build();
  }
}