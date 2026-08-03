package org.purpleBean.kmip.codec.ttlv.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.OtpDigits;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpDigits Ttlv Serialization Tests")
class OtpDigitsTtlvTest extends AbstractTtlvSerializationTestSuite<OtpDigits> {

  @Override
  public Class<OtpDigits> type() {
    return OtpDigits.class;
  }

  @Override
  public OtpDigits createDefault() {
    return OtpDigits.of(123);
  }

  @Override
  public OtpDigits createVariant() {
    return OtpDigits.of(456);
  }
}