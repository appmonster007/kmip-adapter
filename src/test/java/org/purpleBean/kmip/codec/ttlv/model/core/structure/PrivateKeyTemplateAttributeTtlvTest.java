package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PrivateKeyTemplateAttribute Ttlv Serialization Tests")
class PrivateKeyTemplateAttributeTtlvTest extends AbstractTtlvSerializationTestSuite<PrivateKeyTemplateAttribute> {

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
                        .attributeValue(AttributeValueInteger.of(1))
                        .build())
                .build();
    }

    @Override
    protected PrivateKeyTemplateAttribute createVariant() {
        return PrivateKeyTemplateAttribute.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute-variant"))
                        .attributeValue(AttributeValueInteger.of(1))
                        .build())
                .build();
    }
}