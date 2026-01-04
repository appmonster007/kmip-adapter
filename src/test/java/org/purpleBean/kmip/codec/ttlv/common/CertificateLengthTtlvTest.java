package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateLength;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("CertificateLength TTLV Serialization Tests")
class CertificateLengthTtlvTest extends AbstractTtlvSerializationSuite<CertificateLength> {

    @Override
    protected Class<CertificateLength> type() {
        return CertificateLength.class;
    }

    @Override
    protected CertificateLength createDefault() {
        return CertificateLength.builder().value(10).build();
    }

    @Override
    protected CertificateLength createVariant() {
        return CertificateLength.builder().value(15).build();
    }
}
