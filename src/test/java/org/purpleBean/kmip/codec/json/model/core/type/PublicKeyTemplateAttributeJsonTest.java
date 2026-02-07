package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PublicKeyTemplateAttribute Json Serialization Tests")
class PublicKeyTemplateAttributeJsonTest extends AbstractJsonSerializationTestSuite<PublicKeyTemplateAttribute> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<PublicKeyTemplateAttribute> type() {
        return PublicKeyTemplateAttribute.class;
    }

    @Override
    protected PublicKeyTemplateAttribute createDefault() {
        return PublicKeyTemplateAttribute.of("default-string");
    }

    @Override
    protected PublicKeyTemplateAttribute createVariant() {
        return PublicKeyTemplateAttribute.of("variant-string");
    }
}