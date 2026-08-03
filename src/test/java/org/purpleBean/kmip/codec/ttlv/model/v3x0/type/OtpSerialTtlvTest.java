package org.purpleBean.kmip.codec.ttlv.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.type.OtpSerial;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpSerial Ttlv Serialization Tests")
class OtpSerialTtlvTest extends AbstractTtlvSerializationTestSuite<OtpSerial> {

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