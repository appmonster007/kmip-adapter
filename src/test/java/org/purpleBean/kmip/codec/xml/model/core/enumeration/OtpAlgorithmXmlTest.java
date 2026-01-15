package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OtpAlgorithm XML Serialization")
class OtpAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<OtpAlgorithm> {
    @Override
    protected Class<OtpAlgorithm> type() {
        return OtpAlgorithm.class;
    }

    @Override
    protected OtpAlgorithm createDefault() {
        return OtpAlgorithm.Standard.HOTP.inst();
    }

    @Override
    protected OtpAlgorithm createVariant() {
        return OtpAlgorithm.Standard.TOTP.inst();
    }
}
