package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("HashingAlgorithm XML Serialization")
class HashingAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<HashingAlgorithm> {
    @Override
    public Class<HashingAlgorithm> type() {
        return HashingAlgorithm.class;
    }

    @Override
    public HashingAlgorithm createDefault() {
        return HashingAlgorithm.Standard.MD2.inst();
    }

    @Override
    public HashingAlgorithm createVariant() {
        return HashingAlgorithm.Standard.MD4.inst();
    }
}
