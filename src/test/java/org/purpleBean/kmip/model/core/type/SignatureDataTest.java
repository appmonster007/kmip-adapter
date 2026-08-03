package org.purpleBean.kmip.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("SignatureData Domain Tests")
class SignatureDataTest extends AbstractKmipDataTypeTestSuite<SignatureData> {

  @Override
  protected Class<SignatureData> type() {
    return SignatureData.class;
  }

  @Override
  protected SignatureData createDefault() {
    return SignatureData.of(ByteBuffer.wrap("test signature data".getBytes()));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}
