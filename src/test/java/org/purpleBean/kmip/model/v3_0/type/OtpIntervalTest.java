package org.purpleBean.kmip.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("OtpInterval Domain Tests")
class OtpIntervalTest extends AbstractKmipDataTypeTestSuite<OtpInterval> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<OtpInterval> type() {
    return OtpInterval.class;
  }

  @Override
  public OtpInterval createDefault() {
    return OtpInterval.of(1);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTERVAL;
  }
}