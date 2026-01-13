package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ShreddingAlgorithm XML Serialization")
class ShreddingAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<ShreddingAlgorithm> {
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
