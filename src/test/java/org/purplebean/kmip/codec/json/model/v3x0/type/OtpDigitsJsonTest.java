package org.purplebean.kmip.codec.json.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.OtpDigits;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OtpDigits Json Serialization Tests")
class OtpDigitsJsonTest extends AbstractJsonSerializationTestSuite<OtpDigits> {

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