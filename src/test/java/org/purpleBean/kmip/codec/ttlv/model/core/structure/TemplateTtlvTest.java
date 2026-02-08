package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Template;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Template Ttlv Serialization Tests")
class TemplateTtlvTest extends AbstractTtlvSerializationTestSuite<Template> {

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
        return Template.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute"))
                        .attributeValue(AttributeValue.ofInteger(1))
                        .build())
                .build();
    }

    @Override
    public Template createVariant() {
        return Template.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute-variant"))
                        .attributeValue(AttributeValue.ofInteger(1))
                        .build())
                .build();
    }
}