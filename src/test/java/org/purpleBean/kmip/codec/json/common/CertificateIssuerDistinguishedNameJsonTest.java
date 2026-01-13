package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerDistinguishedName JSON Serialization Tests")
class CertificateIssuerDistinguishedNameJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerDistinguishedName> {

    @Override
    protected Class<CertificateIssuerDistinguishedName> type() {
        return CertificateIssuerDistinguishedName.class;
    }

    @Override
    protected CertificateIssuerDistinguishedName createDefault() {
        return CertificateIssuerDistinguishedName.of("CN=Test Issuer");
    }

    @Override
    protected CertificateIssuerDistinguishedName createVariant() {
        return CertificateIssuerDistinguishedName.of("CN=Another Issuer");
    }
}