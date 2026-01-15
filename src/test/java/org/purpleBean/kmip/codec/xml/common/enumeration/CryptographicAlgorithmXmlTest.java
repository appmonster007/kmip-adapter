package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CryptographicAlgorithm XML Serialization")
class CryptographicAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<CryptographicAlgorithm> {
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
