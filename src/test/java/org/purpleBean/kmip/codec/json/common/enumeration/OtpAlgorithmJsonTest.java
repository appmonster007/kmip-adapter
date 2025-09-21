package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("OtpAlgorithm JSON Serialization")
class OtpAlgorithmJsonTest extends AbstractJsonSerializationSuite<OtpAlgorithm> {
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
