package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PrivateKeyTemplateAttribute Xml Serialization Tests")
class PrivateKeyTemplateAttributeXmlTest extends AbstractXmlSerializationTestSuite<PrivateKeyTemplateAttribute> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<PrivateKeyTemplateAttribute> type() {
        return PrivateKeyTemplateAttribute.class;
    }

    @Override
    protected PrivateKeyTemplateAttribute createDefault() {
        return PrivateKeyTemplateAttribute.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute"))
                        .attributeValue(AttributeValue.ofInteger(1))
                        .build())
                .build();
    }

    @Override
    protected PrivateKeyTemplateAttribute createVariant() {
        return PrivateKeyTemplateAttribute.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute-variant"))
                        .attributeValue(AttributeValue.ofInteger(1))
                        .build())
                .build();
    }
}