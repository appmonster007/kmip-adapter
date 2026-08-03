package org.purpleBean.kmip.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("AuthenticatedEncryptionAdditionalData Domain Tests")
class AuthenticatedEncryptionAdditionalDataTest
    extends AbstractKmipDataTypeTestSuite<AuthenticatedEncryptionAdditionalData> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<AuthenticatedEncryptionAdditionalData> type() {
    return AuthenticatedEncryptionAdditionalData.class;
  }

  @Override
  protected AuthenticatedEncryptionAdditionalData createDefault() {
    return AuthenticatedEncryptionAdditionalData.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}