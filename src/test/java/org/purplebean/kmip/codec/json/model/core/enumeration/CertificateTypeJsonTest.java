package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.CertificateType;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateType JSON Serialization")
class CertificateTypeJsonTest extends AbstractJsonSerializationTestSuite<CertificateType> {
  @Override
  public Class<CertificateType> type() {
    return CertificateType.class;
  }

  @Override
  public CertificateType createDefault() {
    return CertificateType.Standard.X_509.inst();
  }

  @Override
  public CertificateType createVariant() {
    return CertificateType.Standard.PGP.inst();
  }
}
