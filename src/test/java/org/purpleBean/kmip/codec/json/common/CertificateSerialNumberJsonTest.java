package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateSerialNumber;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("CertificateSerialNumber JSON Serialization Tests")
class CertificateSerialNumberJsonTest extends AbstractJsonSerializationSuite<CertificateSerialNumber> {

    @Override
    protected Class<CertificateSerialNumber> type() {
        return CertificateSerialNumber.class;
    }

    @Override
    protected CertificateSerialNumber createDefault() {
        return CertificateSerialNumber.of("12345".getBytes());
    }

    @Override
    protected CertificateSerialNumber createVariant() {
        return CertificateSerialNumber.of("67890".getBytes());
    }
}
