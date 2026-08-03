package org.purpleBean.kmip.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("OtpCounter Domain Tests")
class OtpCounterTest extends AbstractKmipDataTypeTestSuite<OtpCounter> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<OtpCounter> type() {
    return OtpCounter.class;
  }

  @Override
  public OtpCounter createDefault() {
    return OtpCounter.of(1);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}