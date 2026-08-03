package org.purpleBean.kmip.model.v3x0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PasswordSalt Domain Tests")
class PasswordSaltTest extends AbstractKmipDataTypeTestSuite<PasswordSalt> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<PasswordSalt> type() {
    return PasswordSalt.class;
  }

  @Override
  public PasswordSalt createDefault() {
    return PasswordSalt.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}