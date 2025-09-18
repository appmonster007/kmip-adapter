package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("DigitalSignatureAlgorithm XML Serialization")
class DigitalSignatureAlgorithmXmlTest extends AbstractXmlSerializationSuite<DigitalSignatureAlgorithm> {
    @Override
    protected Class<DigitalSignatureAlgorithm> type() {
        return DigitalSignatureAlgorithm.class;
    }

    @Override
    protected DigitalSignatureAlgorithm createDefault() {
        return new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.Standard.PLACEHOLDER_1);
    }

    @Override
    protected DigitalSignatureAlgorithm createVariant() {
        return new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.Standard.PLACEHOLDER_2);
    }
}
