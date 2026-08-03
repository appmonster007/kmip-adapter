package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.Template;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Template Xml Serialization Tests")
class TemplateXmlTest extends AbstractXmlSerializationTestSuite<Template> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<Template> type() {
    return Template.class;
  }

  @Override
  public Template createDefault() {
    return Template
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
  }

  @Override
  public Template createVariant() {
    return Template
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute-variant"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
  }
}