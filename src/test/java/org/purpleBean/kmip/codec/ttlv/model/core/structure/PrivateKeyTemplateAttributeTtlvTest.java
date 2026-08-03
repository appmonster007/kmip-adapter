package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PrivateKeyTemplateAttribute Ttlv Serialization Tests")
class PrivateKeyTemplateAttributeTtlvTest
    extends AbstractTtlvSerializationTestSuite<PrivateKeyTemplateAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<PrivateKeyTemplateAttribute> type() {
    return PrivateKeyTemplateAttribute.class;
  }

  @Override
  public PrivateKeyTemplateAttribute createDefault() {
    return PrivateKeyTemplateAttribute
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
  }

  @Override
  public PrivateKeyTemplateAttribute createVariant() {
    return PrivateKeyTemplateAttribute
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute-variant"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
  }
}