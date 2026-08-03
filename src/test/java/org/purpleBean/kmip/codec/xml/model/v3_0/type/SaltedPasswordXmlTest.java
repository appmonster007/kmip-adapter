package org.purpleBean.kmip.codec.xml.model.v3_0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.SaltedPassword;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SaltedPassword Xml Serialization Tests")
class SaltedPasswordXmlTest extends AbstractXmlSerializationTestSuite<SaltedPassword> {

  @Override
  public Class<SaltedPassword> type() {
    return SaltedPassword.class;
  }

  @Override
  public SaltedPassword createDefault() {
    return SaltedPassword.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public SaltedPassword createVariant() {
    return SaltedPassword.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}