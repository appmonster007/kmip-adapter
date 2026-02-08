package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DigitalSignatureAlgorithm XML Serialization")
class DigitalSignatureAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<DigitalSignatureAlgorithm> {
    @Override
    public Class<DigitalSignatureAlgorithm> type() {
        return DigitalSignatureAlgorithm.class;
    }

    @Override
    public DigitalSignatureAlgorithm createDefault() {
        return DigitalSignatureAlgorithm.Standard.MD2_WITH_RSA_ENCRYPTION.inst();
    }

    @Override
    public DigitalSignatureAlgorithm createVariant() {
        return DigitalSignatureAlgorithm.Standard.MD5_WITH_RSA_ENCRYPTION.inst();
    }
}
