package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.AuthenticatedEncryptionTag;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AuthenticatedEncryptionTag Ttlv Serialization Tests")
class AuthenticatedEncryptionTagTtlvTest
    extends AbstractTtlvSerializationTestSuite<AuthenticatedEncryptionTag> {

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