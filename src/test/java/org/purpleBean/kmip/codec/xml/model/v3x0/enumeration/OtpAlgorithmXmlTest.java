package org.purplebean.kmip.codec.xml.model.v3x0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.enumeration.OtpAlgorithm;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OtpAlgorithm XML Serialization")
class OtpAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<OtpAlgorithm> {
  @Override
  public Class<OtpAlgorithm> type() {
    return OtpAlgorithm.class;
  }

  @Override
  public OtpAlgorithm createDefault() {
    return OtpAlgorithm.Standard.HOTP.inst();
  }

  @Override
  public OtpAlgorithm createVariant() {
    return OtpAlgorithm.Standard.TOTP.inst();
  }
}
