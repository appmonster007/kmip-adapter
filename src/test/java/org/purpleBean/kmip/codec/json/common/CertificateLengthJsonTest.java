package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateLength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("CertificateLength JSON Serialization Tests")
class CertificateLengthJsonTest extends AbstractJsonSerializationSuite<CertificateLength> {

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
