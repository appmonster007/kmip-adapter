package org.purpleBean.kmip.codec.json.model.core.structure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdsaPublicKey;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TransparentEcdsaPublicKey JSON Serialization Tests")
class TransparentEcdsaPublicKeyJsonTest
    extends AbstractJsonSerializationTestSuite<TransparentEcdsaPublicKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentEcdsaPublicKey> type() {
    return TransparentEcdsaPublicKey.class;
  }

  @Override
  public TransparentEcdsaPublicKey createDefault() {
    return TransparentEcdsaPublicKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        QString.of("test".getBytes())
    );
  }

  @Override
  public TransparentEcdsaPublicKey createVariant() {
    return TransparentEcdsaPublicKey.of(
        RecommendedCurve.Standard.P_224.inst(),
        QString.of("test2".getBytes())
    );
  }
}