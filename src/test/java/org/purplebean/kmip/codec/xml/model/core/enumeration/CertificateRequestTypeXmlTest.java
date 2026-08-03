package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.CertificateRequestType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateRequestType XML Serialization")
class CertificateRequestTypeXmlTest
    extends AbstractXmlSerializationTestSuite<CertificateRequestType> {
  @Override
  public Class<CertificateRequestType> type() {
    return CertificateRequestType.class;
  }

  @Override
  public CertificateRequestType createDefault() {
    return CertificateRequestType.Standard.CRMF.inst();
  }

  @Override
  public CertificateRequestType createVariant() {
    return CertificateRequestType.Standard.PKCS_10.inst();
  }
}
