package org.purpleBean.kmip.codec.json.model.v2_1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionAdditionalData;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AuthenticatedEncryptionAdditionalData Json Serialization Tests")
class AuthenticatedEncryptionAdditionalDataJsonTest
    extends AbstractJsonSerializationTestSuite<AuthenticatedEncryptionAdditionalData> {

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