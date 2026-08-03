package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.TagLength;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TagLength XML Serialization Tests")
class TagLengthXmlTest extends AbstractXmlSerializationTestSuite<TagLength> {

  @Override
  public Class<TagLength> type() {
    return TagLength.class;
  }

  @Override
  public TagLength createDefault() {
    return TagLength.of(128);
  }

  @Override
  public TagLength createVariant() {
    return TagLength.of(256);
  }
}