package org.purpleBean.kmip.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("OtpSerial Domain Tests")
class OtpSerialTest extends AbstractKmipDataTypeTestSuite<OtpSerial> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<OtpSerial> type() {
    return OtpSerial.class;
  }

  @Override
  public OtpSerial createDefault() {
    return OtpSerial.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}