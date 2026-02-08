package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPrivateKey;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("TransparentEcPrivateKey Ttlv Serialization Tests")
class TransparentEcPrivateKeyTtlvTest extends AbstractTtlvSerializationTestSuite<TransparentEcPrivateKey> {

    @Override
    public Class<TransparentEcPrivateKey> type() {
        return TransparentEcPrivateKey.class;
    }

    @Override
    public TransparentEcPrivateKey createDefault() {
        return TransparentEcPrivateKey.builder()
                .recommendedCurve(RecommendedCurve.Standard.P_192.inst())
                .d(D.of(BigInteger.ONE))
                .build();
    }

    @Override
    public TransparentEcPrivateKey createVariant() {
        return TransparentEcPrivateKey.builder()
                .recommendedCurve(RecommendedCurve.Standard.P_224.inst())
                .d(D.of(BigInteger.TEN))
                .build();
    }
}