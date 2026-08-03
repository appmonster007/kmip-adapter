package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("IVCounterNonce TTLV Serialization Tests")
class IVCounterNonceTtlvTest extends AbstractTtlvSerializationTestSuite<IVCounterNonce> {

  @Override
  public Class<IVCounterNonce> type() {
    return IVCounterNonce.class;
  }

  @Override
  public IVCounterNonce createDefault() {
    return IVCounterNonce.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public IVCounterNonce createVariant() {
    return IVCounterNonce.of(new byte[] {0x04, 0x05, 0x06});
  }
}