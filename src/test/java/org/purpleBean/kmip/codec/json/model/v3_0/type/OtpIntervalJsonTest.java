package org.purpleBean.kmip.codec.json.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.OtpInterval;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OtpInterval Json Serialization Tests")
class OtpIntervalJsonTest extends AbstractJsonSerializationTestSuite<OtpInterval> {

  @Override
  public Class<OtpInterval> type() {
    return OtpInterval.class;
  }

  @Override
  public OtpInterval createDefault() {
    return OtpInterval.of(123);
  }

  @Override
  public OtpInterval createVariant() {
    return OtpInterval.of(456);
  }
}