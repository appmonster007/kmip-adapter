package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("SubjectDistinguishedName Domain Tests")
class SubjectDistinguishedNameTest extends AbstractKmipDataTypeTestSuite<SubjectDistinguishedName> {

  @Override
  protected Class<SubjectDistinguishedName> type() {
    return SubjectDistinguishedName.class;
  }

  @Override
  protected SubjectDistinguishedName createDefault() {
    return SubjectDistinguishedName.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}