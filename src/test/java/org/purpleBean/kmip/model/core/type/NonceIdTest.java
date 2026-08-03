package org.purpleBean.kmip.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("NonceId Domain Tests")
class NonceIdTest extends AbstractKmipDataTypeTestSuite<NonceId> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<NonceId> type() {
    return NonceId.class;
  }

  @Override
  protected NonceId createDefault() {
    return NonceId.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}