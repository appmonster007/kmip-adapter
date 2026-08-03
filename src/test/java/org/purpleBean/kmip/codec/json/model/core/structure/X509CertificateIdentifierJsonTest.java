package org.purpleBean.kmip.codec.json.model.core.structure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.X509CertificateIdentifier;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("X509CertificateIdentifier Json Serialization Tests")
class X509CertificateIdentifierJsonTest
    extends AbstractJsonSerializationTestSuite<X509CertificateIdentifier> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<X509CertificateIdentifier> type() {
    return X509CertificateIdentifier.class;
  }

  @Override
  public X509CertificateIdentifier createDefault() {
    return X509CertificateIdentifier
        .builder()
        .issuerDistinguishedName(IssuerDistinguishedName.of("test-issuer".getBytes()))
        .certificateSerialNumber(CertificateSerialNumber.of("12345".getBytes()))
        .build();
  }

  @Override
  public X509CertificateIdentifier createVariant() {
    return X509CertificateIdentifier
        .builder()
        .issuerDistinguishedName(IssuerDistinguishedName.of("test-issuer-variant".getBytes()))
        .certificateSerialNumber(CertificateSerialNumber.of("67890".getBytes()))
        .build();
  }
}
