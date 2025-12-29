package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateRequest;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("CertificateRequest JSON Serialization Tests")
class CertificateRequestJsonTest extends AbstractJsonSerializationSuite<CertificateRequest> {

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