package org.purpleBean.kmip.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("OtpDigits Domain Tests")
class OtpDigitsTest extends AbstractKmipDataTypeTestSuite<OtpDigits> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<OtpDigits> type() {
    return OtpDigits.class;
  }

  @Override
  public OtpDigits createDefault() {
    return OtpDigits.of(1);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}