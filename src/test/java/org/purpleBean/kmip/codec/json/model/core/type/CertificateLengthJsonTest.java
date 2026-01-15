package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateLength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateLength JSON Serialization Tests")
class CertificateLengthJsonTest extends AbstractJsonSerializationTestSuite<CertificateLength> {

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
