package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("SubjectAlternativeName Domain Tests")
class SubjectAlternativeNameTest extends AbstractKmipDataTypeTestSuite<SubjectAlternativeName> {

  @Override
  protected Class<SubjectAlternativeName> type() {
    return SubjectAlternativeName.class;
  }

  @Override
  protected SubjectAlternativeName createDefault() {
    return SubjectAlternativeName.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}