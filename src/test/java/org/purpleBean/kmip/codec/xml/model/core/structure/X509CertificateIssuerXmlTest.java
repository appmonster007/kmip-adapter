package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.X509CertificateIssuer;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("X509CertificateIssuer Xml Serialization Tests")
class X509CertificateIssuerXmlTest extends AbstractXmlSerializationTestSuite<X509CertificateIssuer> {

    @Override
    public Class<X509CertificateIssuer> type() {
        return X509CertificateIssuer.class;
    }

    @Override
    public X509CertificateIssuer createDefault() {
        return X509CertificateIssuer.builder()
                .issuerDistinguishedName(
                        IssuerDistinguishedName.of("CN=Test Issuer".getBytes())
                )
                .issuerAlternativeName(
                        IssuerAlternativeName.of("alt.issuer.com".getBytes())
                )
                .build();
    }
}