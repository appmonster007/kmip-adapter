package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OtpAlgorithm JSON Serialization")
class OtpAlgorithmJsonTest extends AbstractJsonSerializationTestSuite<OtpAlgorithm> {
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
