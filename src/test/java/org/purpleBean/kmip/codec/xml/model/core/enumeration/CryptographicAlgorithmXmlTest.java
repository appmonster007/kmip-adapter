package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CryptographicAlgorithm XML Serialization")
class CryptographicAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<CryptographicAlgorithm> {
    @Override
    public Class<CryptographicAlgorithm> type() {
        return CryptographicAlgorithm.class;
    }

    @Override
    public CryptographicAlgorithm createDefault() {
        return CryptographicAlgorithm.Standard.DES.inst();
    }

    @Override
    public CryptographicAlgorithm createVariant() {
        return CryptographicAlgorithm.Standard.TRIPLE_DES.inst();
    }
}
