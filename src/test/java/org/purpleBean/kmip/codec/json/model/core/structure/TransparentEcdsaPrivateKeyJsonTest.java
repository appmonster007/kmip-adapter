package org.purplebean.kmip.codec.json.model.core.structure;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdsaPrivateKey;
import org.purplebean.kmip.model.core.type.D;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TransparentEcdsaPrivateKey JSON Serialization Tests")
class TransparentEcdsaPrivateKeyJsonTest
    extends AbstractJsonSerializationTestSuite<TransparentEcdsaPrivateKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentEcdsaPrivateKey> type() {
    return TransparentEcdsaPrivateKey.class;
  }

  @Override
  public TransparentEcdsaPrivateKey createDefault() {
    return TransparentEcdsaPrivateKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        D.of(BigInteger.valueOf(1))
    );
  }

  @Override
  public TransparentEcdsaPrivateKey createVariant() {
    return TransparentEcdsaPrivateKey.of(
        RecommendedCurve.Standard.P_224.inst(),
        D.of(BigInteger.valueOf(2))
    );
  }
}