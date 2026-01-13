package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("HashingAlgorithm XML Serialization")
class HashingAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<HashingAlgorithm> {
    @Override
    protected Class<HashingAlgorithm> type() {
        return HashingAlgorithm.class;
    }

    @Override
    protected HashingAlgorithm createDefault() {
        return new HashingAlgorithm(HashingAlgorithm.Standard.MD2);
    }

    @Override
    protected HashingAlgorithm createVariant() {
        return new HashingAlgorithm(HashingAlgorithm.Standard.MD4);
    }
}
