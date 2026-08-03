package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PublicKeyTemplateAttribute Ttlv Serialization Tests")
class PublicKeyTemplateAttributeTtlvTest
    extends AbstractTtlvSerializationTestSuite<PublicKeyTemplateAttribute> {

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