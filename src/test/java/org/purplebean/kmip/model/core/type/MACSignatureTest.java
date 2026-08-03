package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("MACSignature Domain Tests")
class MACSignatureTest extends AbstractKmipDataTypeTestSuite<MACSignature> {

  @Override
  protected Class<MACSignature> type() {
    return MACSignature.class;
  }

  @Override
  protected MACSignature createDefault() {
    return MACSignature.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}