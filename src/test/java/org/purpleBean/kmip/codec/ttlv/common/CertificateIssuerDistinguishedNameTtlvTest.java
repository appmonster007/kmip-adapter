package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("CertificateIssuerDistinguishedName TTLV Serialization Tests")
class CertificateIssuerDistinguishedNameTtlvTest extends AbstractTtlvSerializationSuite<CertificateIssuerDistinguishedName> {

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