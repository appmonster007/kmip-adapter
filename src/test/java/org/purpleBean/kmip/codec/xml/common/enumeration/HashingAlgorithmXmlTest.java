package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("HashingAlgorithm XML Serialization")
class HashingAlgorithmXmlTest extends AbstractXmlSerializationSuite<HashingAlgorithm> {
    @Override
    protected Class<HashingAlgorithm> type() {
        return HashingAlgorithm.class;
    }

    @Override
    protected HashingAlgorithm createDefault() {
        return new HashingAlgorithm(HashingAlgorithm.Standard.PLACEHOLDER_1);
    }

    @Override
    protected HashingAlgorithm createVariant() {
        return new HashingAlgorithm(HashingAlgorithm.Standard.PLACEHOLDER_2);
    }
}
