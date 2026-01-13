package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPrivateKey;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentEcdsaPrivateKey JSON Serialization Tests")
class TransparentEcdsaPrivateKeyJsonTest extends AbstractJsonSerializationTestSuite<TransparentEcdsaPrivateKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentEcdsaPrivateKey> type() {
        return TransparentEcdsaPrivateKey.class;
    }

    @Override
    protected TransparentEcdsaPrivateKey createDefault() {
        return TransparentEcdsaPrivateKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
                D.of(BigInteger.valueOf(1))
        );
    }

    @Override
    protected TransparentEcdsaPrivateKey createVariant() {
        return TransparentEcdsaPrivateKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_224),
                D.of(BigInteger.valueOf(2))
        );
    }
}