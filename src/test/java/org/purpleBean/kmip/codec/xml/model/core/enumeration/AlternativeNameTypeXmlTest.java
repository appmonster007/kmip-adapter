package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AlternativeNameType XML Serialization")
class AlternativeNameTypeXmlTest extends AbstractXmlSerializationTestSuite<AlternativeNameType> {
  @Override
  public Class<AlternativeNameType> type() {
    return AlternativeNameType.class;
  }

  @Override
  public AlternativeNameType createDefault() {
    return AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
  }

  @Override
  public AlternativeNameType createVariant() {
    return AlternativeNameType.Standard.URI.inst();
  }
}
