package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("CertificateIssuerDistinguishedName XML Serialization Tests")
class CertificateIssuerDistinguishedNameXmlTest extends AbstractXmlSerializationSuite<CertificateIssuerDistinguishedName> {

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