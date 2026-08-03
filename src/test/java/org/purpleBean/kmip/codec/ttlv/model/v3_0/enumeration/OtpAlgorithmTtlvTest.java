package org.purpleBean.kmip.codec.ttlv.model.v3_0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpAlgorithm TTLV Serialization")
class OtpAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<OtpAlgorithm> {
  @Override
  public Class<OtpAlgorithm> type() {
    return OtpAlgorithm.class;
  }

  @Override
  public OtpAlgorithm createDefault() {
    return OtpAlgorithm.Standard.HOTP.inst();
  }

  @Override
  public OtpAlgorithm createVariant() {
    return OtpAlgorithm.Standard.TOTP.inst();
  }
}
