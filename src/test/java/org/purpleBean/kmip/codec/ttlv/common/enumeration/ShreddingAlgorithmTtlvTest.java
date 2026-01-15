package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ShreddingAlgorithm TTLV Serialization")
class ShreddingAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<ShreddingAlgorithm> {
    @Override
    protected Class<ShreddingAlgorithm> type() {
        return ShreddingAlgorithm.class;
    }

    @Override
    protected ShreddingAlgorithm createDefault() {
        return ShreddingAlgorithm.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected ShreddingAlgorithm createVariant() {
        return ShreddingAlgorithm.Standard.CRYPTOGRAPHIC.inst();
    }
}
