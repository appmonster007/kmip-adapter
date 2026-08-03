package org.purpleBean.kmip.codec.ttlv.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.type.OtpInterval;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpInterval Ttlv Serialization Tests")
class OtpIntervalTtlvTest extends AbstractTtlvSerializationTestSuite<OtpInterval> {

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