package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CertificateType;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.CertificateValue;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Certificate Xml Serialization Tests")
class CertificateXmlTest extends AbstractXmlSerializationTestSuite<Certificate> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<Certificate> type() {
    return Certificate.class;
  }

  @Override
  public Certificate createDefault() {
    return Certificate
        .builder()
        .certificateType(CertificateType.Standard.X_509.inst())
        .certificateValue(CertificateValue.of(new byte[0]))
        .build();
  }

  @Override
  public Certificate createVariant() {
    return Certificate
        .builder()
        .certificateType(CertificateType.Standard.PGP.inst())
        .certificateValue(CertificateValue.of(new byte[1]))
        .build();
  }
}