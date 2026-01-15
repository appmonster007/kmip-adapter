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
        return HashingAlgorithm.Standard.MD2.inst();
    }

    @Override
    protected HashingAlgorithm createVariant() {
        return HashingAlgorithm.Standard.MD4.inst();
    }
}
