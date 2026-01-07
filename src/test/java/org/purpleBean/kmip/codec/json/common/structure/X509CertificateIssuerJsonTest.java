package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.X509CertificateIssuer;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("X509CertificateIssuer Json Serialization Tests")
class X509CertificateIssuerJsonTest extends AbstractJsonSerializationSuite<X509CertificateIssuer> {

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