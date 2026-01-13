package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpAlgorithm TTLV Serialization")
class OtpAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<OtpAlgorithm> {
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
