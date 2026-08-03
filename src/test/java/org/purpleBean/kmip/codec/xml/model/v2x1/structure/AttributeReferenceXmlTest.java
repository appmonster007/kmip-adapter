package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeReference Xml Serialization Tests")
class AttributeReferenceXmlTest extends AbstractXmlSerializationTestSuite<AttributeReference> {

  @Override
  public Class<AttributeReference> type() {
    return AttributeReference.class;
  }

  @Override
  public AttributeReference createDefault() {
    return AttributeReference
        .builder()
        .build();
  }

  @Override
  public AttributeReference createVariant() {
    return AttributeReference
        .builder()
        .build();
  }
}