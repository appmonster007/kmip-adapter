package org.purpleBean.kmip.codec.json.model.v3_0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.OtpSeed;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OtpSeed Json Serialization Tests")
class OtpSeedJsonTest extends AbstractJsonSerializationTestSuite<OtpSeed> {

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