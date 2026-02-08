package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateValue TTLV Serialization Tests")
class CertificateValueTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateValue> {

    @Override
    public Class<CertificateValue> type() {
        return CertificateValue.class;
    }

    @Override
    public CertificateValue createDefault() {
        return CertificateValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public CertificateValue createVariant() {
        return CertificateValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}