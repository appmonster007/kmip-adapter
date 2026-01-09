package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdhPrivateKey;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentEcdhPrivateKey JSON Serialization Tests")
class TransparentEcdhPrivateKeyJsonTest extends AbstractJsonSerializationSuite<TransparentEcdhPrivateKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentEcdhPrivateKey> type() {
        return TransparentEcdhPrivateKey.class;
    }

    @Override
    protected TransparentEcdhPrivateKey createDefault() {
        return TransparentEcdhPrivateKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
                D.of(BigInteger.valueOf(1))
        );
    }

    @Override
    protected TransparentEcdhPrivateKey createVariant() {
        return TransparentEcdhPrivateKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_224),
                D.of(BigInteger.valueOf(2))
        );
    }
}