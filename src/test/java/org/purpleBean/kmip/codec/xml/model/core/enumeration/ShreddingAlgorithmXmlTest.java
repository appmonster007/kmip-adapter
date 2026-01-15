package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ShreddingAlgorithm XML Serialization")
class ShreddingAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<ShreddingAlgorithm> {
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
