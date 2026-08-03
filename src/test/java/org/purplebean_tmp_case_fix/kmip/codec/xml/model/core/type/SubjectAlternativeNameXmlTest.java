package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.SubjectAlternativeName;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SubjectAlternativeName XML Serialization Tests")
class SubjectAlternativeNameXmlTest
    extends AbstractXmlSerializationTestSuite<SubjectAlternativeName> {

  @Override
  public Class<SubjectAlternativeName> type() {
    return SubjectAlternativeName.class;
  }

  @Override
  public SubjectAlternativeName createDefault() {
    return SubjectAlternativeName.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public SubjectAlternativeName createVariant() {
    return SubjectAlternativeName.of(new byte[] {0x04, 0x05, 0x06});
  }
}