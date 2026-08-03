package org.purplebean.kmip.codec.xml.model.v3x0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.OtpSeed;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OtpSeed Xml Serialization Tests")
class OtpSeedXmlTest extends AbstractXmlSerializationTestSuite<OtpSeed> {

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