package org.purpleBean.kmip.codec.ttlv.model.v3x0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.type.OtpSeed;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OtpSeed Ttlv Serialization Tests")
class OtpSeedTtlvTest extends AbstractTtlvSerializationTestSuite<OtpSeed> {

  @Override
  public Class<OtpSeed> type() {
    return OtpSeed.class;
  }

  @Override
  public OtpSeed createDefault() {
    return OtpSeed.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public OtpSeed createVariant() {
    return OtpSeed.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}