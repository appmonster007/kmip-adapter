package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CommonTemplateAttribute Ttlv Serialization Tests")
class CommonTemplateAttributeTtlvTest extends AbstractTtlvSerializationTestSuite<CommonTemplateAttribute> {

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