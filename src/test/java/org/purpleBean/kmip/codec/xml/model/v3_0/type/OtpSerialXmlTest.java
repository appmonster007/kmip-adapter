package org.purpleBean.kmip.codec.xml.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.OtpSerial;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OtpSerial Xml Serialization Tests")
class OtpSerialXmlTest extends AbstractXmlSerializationTestSuite<OtpSerial> {

  @Override
  public Class<OtpSerial> type() {
    return OtpSerial.class;
  }

  @Override
  public OtpSerial createDefault() {
    return OtpSerial.of("default-string");
  }

  @Override
  public OtpSerial createVariant() {
    return OtpSerial.of("variant-string");
  }
}