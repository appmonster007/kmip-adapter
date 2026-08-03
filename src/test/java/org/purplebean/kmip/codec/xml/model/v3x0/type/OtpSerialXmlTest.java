package org.purplebean.kmip.codec.xml.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.OtpSerial;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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