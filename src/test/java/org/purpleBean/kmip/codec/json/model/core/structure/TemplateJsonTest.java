package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Template;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Template Json Serialization Tests")
class TemplateJsonTest extends AbstractJsonSerializationTestSuite<Template> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<Template> type() {
        return Template.class;
    }

    @Override
    protected Template createDefault() {
        return Template.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute"))
                        .attributeValue(AttributeValueInteger.of(1))
                        .build())
                .build();
    }

    @Override
    protected Template createVariant() {
        return Template.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute-variant"))
                        .attributeValue(AttributeValueInteger.of(1))
                        .build())
                .build();
    }
}