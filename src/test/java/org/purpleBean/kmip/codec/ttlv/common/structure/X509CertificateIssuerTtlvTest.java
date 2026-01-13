package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.IssuerAlternativeName;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIssuer;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("X509CertificateIssuer Ttlv Serialization Tests")
class X509CertificateIssuerTtlvTest extends AbstractTtlvSerializationTestSuite<X509CertificateIssuer> {

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