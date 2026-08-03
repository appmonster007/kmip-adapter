package org.purpleBean.kmip.codec.xml.model.v3_0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.PasswordSalt;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PasswordSalt Xml Serialization Tests")
class PasswordSaltXmlTest extends AbstractXmlSerializationTestSuite<PasswordSalt> {

  @Override
  public Class<PasswordSalt> type() {
    return PasswordSalt.class;
  }

  @Override
  public PasswordSalt createDefault() {
    return PasswordSalt.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public PasswordSalt createVariant() {
    return PasswordSalt.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}