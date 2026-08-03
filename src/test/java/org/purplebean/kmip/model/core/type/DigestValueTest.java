package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("DigestValue Domain Tests")
class DigestValueTest extends AbstractKmipDataTypeTestSuite<DigestValue> {

  @Override
  protected Class<DigestValue> type() {
    return DigestValue.class;
  }

  @Override
  protected DigestValue createDefault() {
    return DigestValue.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}