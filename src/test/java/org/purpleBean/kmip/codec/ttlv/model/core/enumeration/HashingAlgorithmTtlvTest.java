package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("HashingAlgorithm TTLV Serialization")
class HashingAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<HashingAlgorithm> {
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
