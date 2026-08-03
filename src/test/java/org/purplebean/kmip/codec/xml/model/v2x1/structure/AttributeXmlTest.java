package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.Attribute;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Attribute Xml Serialization Tests")
class AttributeXmlTest extends AbstractXmlSerializationTestSuite<Attribute> {

  @Override
  public Class<Attribute> type() {
    return Attribute.class;
  }

  @Override
  public Attribute createDefault() {
    return Attribute
        .builder()
        .vendorIdentification(org.purplebean.kmip.model.core.type.VendorIdentification.of("vendor"))
        .attributeName(org.purplebean.kmip.model.core.type.AttributeName.of("TestAttr"))
        .build();
  }

  @Override
  public Attribute createVariant() {
    return Attribute
        .builder()
        .vendorIdentification(org.purplebean.kmip.model.core.type.VendorIdentification.of("vendor"))
        .attributeName(org.purplebean.kmip.model.core.type.AttributeName.of("TestAttr"))
        .build();
  }
}