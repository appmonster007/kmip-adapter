package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateSerialNumber;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("X509CertificateIdentifier Xml Serialization Tests")
class X509CertificateIdentifierXmlTest extends AbstractXmlSerializationTestSuite<X509CertificateIdentifier> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<X509CertificateIdentifier> type() {
        return X509CertificateIdentifier.class;
    }

    @Override
    protected X509CertificateIdentifier createDefault() {
        return X509CertificateIdentifier.builder()
                .issuerDistinguishedName(IssuerDistinguishedName.of("test-issuer".getBytes()))
                .certificateSerialNumber(CertificateSerialNumber.of("12345".getBytes()))
                .build();
    }

    @Override
    protected X509CertificateIdentifier createVariant() {
        return X509CertificateIdentifier.builder()
                .issuerDistinguishedName(IssuerDistinguishedName.of("test-issuer-variant".getBytes()))
                .certificateSerialNumber(CertificateSerialNumber.of("67890".getBytes()))
                .build();
    }
}
