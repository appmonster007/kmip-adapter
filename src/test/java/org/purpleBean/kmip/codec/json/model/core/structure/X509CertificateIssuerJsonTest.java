package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;
import org.purpleBean.kmip.model.core.structure.X509CertificateIssuer;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("X509CertificateIssuer Json Serialization Tests")
class X509CertificateIssuerJsonTest extends AbstractJsonSerializationTestSuite<X509CertificateIssuer> {

    @Override
    protected Class<X509CertificateIssuer> type() {
        return X509CertificateIssuer.class;
    }

    @Override
    protected X509CertificateIssuer createDefault() {
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