package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPrivateKey;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("TransparentEcPrivateKey Xml Serialization Tests")
class TransparentEcPrivateKeyXmlTest extends AbstractXmlSerializationTestSuite<TransparentEcPrivateKey> {

    @Override
    protected Class<TransparentEcPrivateKey> type() {
        return TransparentEcPrivateKey.class;
    }

    @Override
    protected TransparentEcPrivateKey createDefault() {
        return TransparentEcPrivateKey.builder()
                .recommendedCurve(RecommendedCurve.Standard.P_192.inst())
                .d(D.of(BigInteger.ONE))
                .build();
    }

    @Override
    protected TransparentEcPrivateKey createVariant() {
        return TransparentEcPrivateKey.builder()
                .recommendedCurve(RecommendedCurve.Standard.P_224.inst())
                .d(D.of(BigInteger.TEN))
                .build();
    }
}