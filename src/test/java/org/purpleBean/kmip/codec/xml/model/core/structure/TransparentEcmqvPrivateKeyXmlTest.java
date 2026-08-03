package org.purpleBean.kmip.codec.xml.model.core.structure;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcmqvPrivateKey;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TransparentEcmqvPrivateKey XML Serialization Tests")
class TransparentEcmqvPrivateKeyXmlTest
    extends AbstractXmlSerializationTestSuite<TransparentEcmqvPrivateKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentEcmqvPrivateKey> type() {
    return TransparentEcmqvPrivateKey.class;
  }

  @Override
  public TransparentEcmqvPrivateKey createDefault() {
    return TransparentEcmqvPrivateKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        D.of(BigInteger.valueOf(1))
    );
  }

  @Override
  public TransparentEcmqvPrivateKey createVariant() {
    return TransparentEcmqvPrivateKey.of(
        RecommendedCurve.Standard.P_224.inst(),
        D.of(BigInteger.valueOf(2))
    );
  }
}