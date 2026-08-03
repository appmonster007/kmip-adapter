package org.purplebean.kmip.model.v3x0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

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