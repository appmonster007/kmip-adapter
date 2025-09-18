package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("HashingAlgorithm JSON Serialization")
class HashingAlgorithmJsonTest extends AbstractJsonSerializationSuite<HashingAlgorithm> {
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
