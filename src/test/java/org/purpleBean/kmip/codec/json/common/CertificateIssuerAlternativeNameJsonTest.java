package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("CertificateIssuerAlternativeName JSON Serialization Tests")
class CertificateIssuerAlternativeNameJsonTest extends AbstractJsonSerializationSuite<CertificateIssuerAlternativeName> {

    @Override
    protected Class<CertificateIssuerAlternativeName> type() {
        return CertificateIssuerAlternativeName.class;
    }

    @Override
    protected CertificateIssuerAlternativeName createDefault() {
        return CertificateIssuerAlternativeName.builder().value("test-issuer-alt-name").build();
    }

    @Override
    protected CertificateIssuerAlternativeName createVariant() {
        return CertificateIssuerAlternativeName.builder().value("another-issuer-alt-name").build();
    }
}