package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("IVCounterNonce JSON Serialization Tests")
class IVCounterNonceJsonTest extends AbstractJsonSerializationTestSuite<IVCounterNonce> {

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