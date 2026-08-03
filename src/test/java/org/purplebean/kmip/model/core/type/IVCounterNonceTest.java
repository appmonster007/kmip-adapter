package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("IVCounterNonce Domain Tests")
class IVCounterNonceTest extends AbstractKmipDataTypeTestSuite<IVCounterNonce> {

  @Override
  protected Class<IVCounterNonce> type() {
    return IVCounterNonce.class;
  }

  @Override
  protected IVCounterNonce createDefault() {
    return IVCounterNonce.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}