package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CommonTemplateAttribute Ttlv Serialization Tests")
class CommonTemplateAttributeTtlvTest
    extends AbstractTtlvSerializationTestSuite<CommonTemplateAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CommonTemplateAttribute> type() {
    return CommonTemplateAttribute.class;
  }

  @Override
  public CommonTemplateAttribute createDefault() {
    return CommonTemplateAttribute
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
  }

  @Override
  public CommonTemplateAttribute createVariant() {
    return CommonTemplateAttribute
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute-variant"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
  }
}