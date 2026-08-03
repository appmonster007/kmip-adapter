package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.AuthenticatedEncryptionAdditionalData;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AuthenticatedEncryptionAdditionalData Ttlv Serialization Tests")
class AuthenticatedEncryptionAdditionalDataTtlvTest
    extends AbstractTtlvSerializationTestSuite<AuthenticatedEncryptionAdditionalData> {

  @Override
  public Class<AuthenticatedEncryptionAdditionalData> type() {
    return AuthenticatedEncryptionAdditionalData.class;
  }

  @Override
  public AuthenticatedEncryptionAdditionalData createDefault() {
    return AuthenticatedEncryptionAdditionalData.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public AuthenticatedEncryptionAdditionalData createVariant() {
    return AuthenticatedEncryptionAdditionalData.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}