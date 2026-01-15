package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CryptographicAlgorithm TTLV Serialization")
class CryptographicAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<CryptographicAlgorithm> {
    @Override
    protected Class<CryptographicAlgorithm> type() {
        return CryptographicAlgorithm.class;
    }

    @Override
    protected CryptographicAlgorithm createDefault() {
        return CryptographicAlgorithm.Standard.AES.inst();
    }

    @Override
    protected CryptographicAlgorithm createVariant() {
        return CryptographicAlgorithm.Standard.TRIPLE_DES.inst();
    }
}
