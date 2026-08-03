package org.purpleBean.kmip.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("OtpDigest Domain Tests")
class OtpDigestTest extends AbstractKmipDataTypeTestSuite<OtpDigest> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<OtpDigest> type() {
    return OtpDigest.class;
  }

  @Override
  public OtpDigest createDefault() {
    return OtpDigest.of(CryptographicAlgorithm.Standard.AES);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.ENUMERATION;
  }
}