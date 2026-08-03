package org.purpleBean.kmip.codec.ttlv.model.v3_0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.HashedUsernamePassword;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("HashedUsernamePassword Ttlv Serialization Tests")
class HashedUsernamePasswordTtlvTest
    extends AbstractTtlvSerializationTestSuite<HashedUsernamePassword> {

  @Override
  public Class<HashedUsernamePassword> type() {
    return HashedUsernamePassword.class;
  }

  @Override
  public HashedUsernamePassword createDefault() {
    return HashedUsernamePassword.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public HashedUsernamePassword createVariant() {
    return HashedUsernamePassword.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}