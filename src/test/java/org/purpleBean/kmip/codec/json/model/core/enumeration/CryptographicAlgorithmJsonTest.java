package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CryptographicAlgorithm JSON Serialization")
class CryptographicAlgorithmJsonTest extends AbstractJsonSerializationTestSuite<CryptographicAlgorithm> {
    @Override
    protected Class<CryptographicAlgorithm> type() {
        return CryptographicAlgorithm.class;
    }

    @Override
    protected CryptographicAlgorithm createDefault() {
        return CryptographicAlgorithm.Standard.DES.inst();
    }

    @Override
    protected CryptographicAlgorithm createVariant() {
        return CryptographicAlgorithm.Standard.TRIPLE_DES.inst();
    }
}
