package org.purpleBean.kmip.model.v3_0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("HashedPasswordUsername Domain Tests")
class HashedPasswordUsernameTest extends AbstractKmipDataTypeTestSuite<HashedPasswordUsername> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<HashedPasswordUsername> type() {
    return HashedPasswordUsername.class;
  }

  @Override
  public HashedPasswordUsername createDefault() {
    return HashedPasswordUsername.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}