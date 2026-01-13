package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OtpAlgorithm XML Serialization")
class OtpAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<OtpAlgorithm> {
    @Override
    protected Class<OtpAlgorithm> type() {
        return OtpAlgorithm.class;
    }

    @Override
    protected OtpAlgorithm createDefault() {
        return new OtpAlgorithm(OtpAlgorithm.Standard.HOTP);
    }

    @Override
    protected OtpAlgorithm createVariant() {
        return new OtpAlgorithm(OtpAlgorithm.Standard.TOTP);
    }
}
