package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.CertificateIssuer;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateIssuer Ttlv Serialization Tests")
class CertificateIssuerTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateIssuer> {

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