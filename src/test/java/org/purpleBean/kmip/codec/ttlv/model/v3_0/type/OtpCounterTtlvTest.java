package org.purpleBean.kmip.codec.ttlv.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.OtpCounter;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpCounter Ttlv Serialization Tests")
class OtpCounterTtlvTest extends AbstractTtlvSerializationTestSuite<OtpCounter> {

  @Override
  public Class<OtpCounter> type() {
    return OtpCounter.class;
  }

  @Override
  public OtpCounter createDefault() {
    return OtpCounter.of(123);
  }

  @Override
  public OtpCounter createVariant() {
    return OtpCounter.of(456);
  }
}