package org.purplebean.kmip.codec.xml.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.v3x0.type.OtpDigest;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OtpDigest Xml Serialization Tests")
class OtpDigestXmlTest extends AbstractXmlSerializationTestSuite<OtpDigest> {

  @Override
  public Class<OtpDigest> type() {
    return OtpDigest.class;
  }

  @Override
  public OtpDigest createDefault() {
    return OtpDigest.of(CryptographicAlgorithm.Standard.AES);
  }

  @Override
  public OtpDigest createVariant() {
    return OtpDigest.of(CryptographicAlgorithm.Standard.RSA);
  }
}