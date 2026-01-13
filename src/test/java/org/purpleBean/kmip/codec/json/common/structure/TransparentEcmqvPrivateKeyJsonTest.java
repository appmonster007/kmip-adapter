package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcmqvPrivateKey;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TransparentEcmqvPrivateKey JSON Serialization Tests")
class TransparentEcmqvPrivateKeyJsonTest extends AbstractJsonSerializationTestSuite<TransparentEcmqvPrivateKey> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TransparentEcmqvPrivateKey> type() {
        return TransparentEcmqvPrivateKey.class;
    }

    @Override
    protected TransparentEcmqvPrivateKey createDefault() {
        return TransparentEcmqvPrivateKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
                D.of(BigInteger.valueOf(1))
        );
    }

    @Override
    protected TransparentEcmqvPrivateKey createVariant() {
        return TransparentEcmqvPrivateKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_224),
                D.of(BigInteger.valueOf(2))
        );
    }
}