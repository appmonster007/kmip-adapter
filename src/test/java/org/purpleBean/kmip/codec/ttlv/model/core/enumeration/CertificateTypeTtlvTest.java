package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateType TTLV Serialization")
class CertificateTypeTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateType> {
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
