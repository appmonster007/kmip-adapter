package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PublicKeyTemplateAttribute Xml Serialization Tests")
class PublicKeyTemplateAttributeXmlTest
    extends AbstractXmlSerializationTestSuite<PublicKeyTemplateAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<PublicKeyTemplateAttribute> type() {
    return PublicKeyTemplateAttribute.class;
  }

  @Override
  public PublicKeyTemplateAttribute createDefault() {
    return PublicKeyTemplateAttribute
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
  }

  @Override
  public PublicKeyTemplateAttribute createVariant() {
    return PublicKeyTemplateAttribute
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute-variant"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
  }
}