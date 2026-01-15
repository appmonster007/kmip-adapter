package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateRequest XML Serialization Tests")
class CertificateRequestXmlTest extends AbstractXmlSerializationTestSuite<CertificateRequest> {

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