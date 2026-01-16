package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CommonTemplateAttribute Json Serialization Tests")
class CommonTemplateAttributeJsonTest extends AbstractJsonSerializationTestSuite<CommonTemplateAttribute> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CommonTemplateAttribute> type() {
        return CommonTemplateAttribute.class;
    }

    @Override
    protected CommonTemplateAttribute createDefault() {
        return CommonTemplateAttribute.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute"))
                        .attributeValue(AttributeValueInteger.of(1))
                        .build())
                .build();
    }

    @Override
    protected CommonTemplateAttribute createVariant() {
        return CommonTemplateAttribute.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute-variant"))
                        .attributeValue(AttributeValueInteger.of(1))
                        .build())
                .build();
    }
}