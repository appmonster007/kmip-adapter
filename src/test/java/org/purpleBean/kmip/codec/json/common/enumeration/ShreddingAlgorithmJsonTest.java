package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ShreddingAlgorithm JSON Serialization")
class ShreddingAlgorithmJsonTest extends AbstractJsonSerializationTestSuite<ShreddingAlgorithm> {
    @Override
    protected Class<ShreddingAlgorithm> type() {
        return ShreddingAlgorithm.class;
    }

    @Override
    protected ShreddingAlgorithm createDefault() {
        return new ShreddingAlgorithm(ShreddingAlgorithm.Standard.UNSPECIFIED);
    }

    @Override
    protected ShreddingAlgorithm createVariant() {
        return new ShreddingAlgorithm(ShreddingAlgorithm.Standard.CRYPTOGRAPHIC);
    }
}
