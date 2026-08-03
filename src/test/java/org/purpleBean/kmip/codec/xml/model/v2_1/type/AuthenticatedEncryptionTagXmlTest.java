package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionTag;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AuthenticatedEncryptionTag Xml Serialization Tests")
class AuthenticatedEncryptionTagXmlTest
    extends AbstractXmlSerializationTestSuite<AuthenticatedEncryptionTag> {

  @Override
  public Class<AuthenticatedEncryptionTag> type() {
    return AuthenticatedEncryptionTag.class;
  }

  @Override
  public AuthenticatedEncryptionTag createDefault() {
    return AuthenticatedEncryptionTag.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public AuthenticatedEncryptionTag createVariant() {
    return AuthenticatedEncryptionTag.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}