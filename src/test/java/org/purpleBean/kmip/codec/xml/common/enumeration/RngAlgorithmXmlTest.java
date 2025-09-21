package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("RngAlgorithm XML Serialization")
class RngAlgorithmXmlTest extends AbstractXmlSerializationSuite<RngAlgorithm> {
    @Override
    protected Class<RngAlgorithm> type() {
        return RngAlgorithm.class;
    }

    @Override
    protected RngAlgorithm createDefault() {
        return new RngAlgorithm(RngAlgorithm.Standard.UNSPECIFIED);
    }

    @Override
    protected RngAlgorithm createVariant() {
        return new RngAlgorithm(RngAlgorithm.Standard.FIPS_186_2);
    }
}
