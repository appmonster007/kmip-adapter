package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.NonceId;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NonceId Ttlv Serialization Tests")
class NonceIdTtlvTest extends AbstractTtlvSerializationTestSuite<NonceId> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<NonceId> type() {
    return NonceId.class;
  }

  @Override
  public NonceId createDefault() {
    return NonceId.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public NonceId createVariant() {
    return NonceId.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}