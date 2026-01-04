package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("CertificateValue TTLV Serialization Tests")
class CertificateValueTtlvTest extends AbstractTtlvSerializationSuite<CertificateValue> {

    @Override
    protected Class<CertificateValue> type() {
        return CertificateValue.class;
    }

    @Override
    protected CertificateValue createDefault() {
        return CertificateValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected CertificateValue createVariant() {
        return CertificateValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}