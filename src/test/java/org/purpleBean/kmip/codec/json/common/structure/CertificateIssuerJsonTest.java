package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.CertificateIssuer;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuer Json Serialization Tests")
class CertificateIssuerJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuer> {

    @Override
    protected Class<CertificateIssuer> type() {
        return CertificateIssuer.class;
    }

    @Override
    protected CertificateIssuer createDefault() {
        return CertificateIssuer.builder()
                .certificateIssuerDistinguishedName(
                        CertificateIssuerDistinguishedName.of("CN=Test Issuer")
                )
                .certificateIssuerAlternativeName(
                        CertificateIssuerAlternativeName.of("alt.issuer.com")
                )
                .build();
    }
}