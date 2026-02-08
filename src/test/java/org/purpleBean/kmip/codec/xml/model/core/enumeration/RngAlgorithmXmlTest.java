package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RngAlgorithm XML Serialization")
class RngAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<RngAlgorithm> {
    @Override
    public Class<RngAlgorithm> type() {
        return RngAlgorithm.class;
    }

    @Override
    public RngAlgorithm createDefault() {
        return RngAlgorithm.Standard.UNSPECIFIED.inst();
    }

    @Override
    public RngAlgorithm createVariant() {
        return RngAlgorithm.Standard.FIPS_186_2.inst();
    }
}
