package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("CryptographicAlgorithm TTLV Serialization")
class CryptographicAlgorithmTtlvTest extends AbstractTtlvSerializationSuite<CryptographicAlgorithm> {
    @Override
    protected Class<CryptographicAlgorithm> type() {
        return CryptographicAlgorithm.class;
    }

    @Override
    protected CryptographicAlgorithm createDefault() {
        return new CryptographicAlgorithm(CryptographicAlgorithm.Standard.AES);
    }

    @Override
    protected CryptographicAlgorithm createVariant() {
        return new CryptographicAlgorithm(CryptographicAlgorithm.Standard.TRIPLE_DES);
    }
}
