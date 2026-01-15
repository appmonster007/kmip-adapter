package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSerialNumber TTLV Serialization Tests")
class CertificateSerialNumberTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateSerialNumber> {

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
