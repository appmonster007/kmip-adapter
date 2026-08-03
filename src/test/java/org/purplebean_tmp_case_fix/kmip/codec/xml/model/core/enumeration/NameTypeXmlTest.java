package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.NameType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NameType XML Serialization")
class NameTypeXmlTest extends AbstractXmlSerializationTestSuite<NameType> {
  @Override
  public Class<NameType> type() {
    return NameType.class;
  }

  @Override
  public NameType createDefault() {
    return NameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
  }

  @Override
  public NameType createVariant() {
    return NameType.Standard.URI.inst();
  }
}
