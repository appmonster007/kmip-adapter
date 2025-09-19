package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("CryptographicAlgorithm XML Serialization")
class CryptographicAlgorithmXmlTest extends AbstractXmlSerializationSuite<CryptographicAlgorithm> {
    @Override
    protected Class<CryptographicAlgorithm> type() {
        return CryptographicAlgorithm.class;
    }

    @Override
    protected CryptographicAlgorithm createDefault() {
        return new CryptographicAlgorithm(CryptographicAlgorithm.Standard.DES);
    }

    @Override
    protected CryptographicAlgorithm createVariant() {
        return new CryptographicAlgorithm(CryptographicAlgorithm.Standard.TRIPLE_DES);
    }
}
