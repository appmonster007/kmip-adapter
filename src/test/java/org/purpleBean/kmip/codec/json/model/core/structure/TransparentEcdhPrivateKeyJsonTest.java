package org.purpleBean.kmip.codec.json.model.core.structure;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdhPrivateKey;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TransparentEcdhPrivateKey JSON Serialization Tests")
class TransparentEcdhPrivateKeyJsonTest
    extends AbstractJsonSerializationTestSuite<TransparentEcdhPrivateKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentEcdhPrivateKey> type() {
    return TransparentEcdhPrivateKey.class;
  }

  @Override
  public TransparentEcdhPrivateKey createDefault() {
    return TransparentEcdhPrivateKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        D.of(BigInteger.valueOf(1))
    );
  }

  @Override
  public TransparentEcdhPrivateKey createVariant() {
    return TransparentEcdhPrivateKey.of(
        RecommendedCurve.Standard.P_224.inst(),
        D.of(BigInteger.valueOf(2))
    );
  }
}