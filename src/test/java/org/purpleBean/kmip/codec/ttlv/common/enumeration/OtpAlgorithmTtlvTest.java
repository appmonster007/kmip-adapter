package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("OtpAlgorithm TTLV Serialization")
class OtpAlgorithmTtlvTest extends AbstractTtlvSerializationSuite<OtpAlgorithm> {
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
