package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateRequest;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("CertificateRequest XML Serialization Tests")
class CertificateRequestXmlTest extends AbstractXmlSerializationSuite<CertificateRequest> {

    @Override
    protected Class<CertificateRequest> type() {
        return CertificateRequest.class;
    }

    @Override
    protected CertificateRequest createDefault() {
        return CertificateRequest.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected CertificateRequest createVariant() {
        return CertificateRequest.of(new byte[]{0x04, 0x05, 0x06});
    }
}