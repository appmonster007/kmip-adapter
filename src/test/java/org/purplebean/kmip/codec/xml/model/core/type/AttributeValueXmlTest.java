package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeValue Xml Serialization Tests")
class AttributeValueXmlTest extends AbstractXmlSerializationTestSuite<AttributeValue> {

  @Override
  public Class<AttributeValue> type() {
    return AttributeValue.class;
  }

  @Override
  public AttributeValue createDefault() {
    return AttributeValue.ofTextString("default-string");
  }

  @Override
  public AttributeValue createVariant() {
    return AttributeValue.ofInteger(123);
  }
}