package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("CertificateValue Domain Tests")
class CertificateValueTest extends AbstractKmipDataTypeTestSuite<CertificateValue> {

    @Override
    protected Class<CertificateValue> type() {
        return CertificateValue.class;
    }

    @Override
    protected CertificateValue createDefault() {
        return CertificateValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}