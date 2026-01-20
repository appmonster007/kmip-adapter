package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PrivateKeyTemplateAttribute Json Serialization Tests")
class PrivateKeyTemplateAttributeJsonTest extends AbstractJsonSerializationTestSuite<PrivateKeyTemplateAttribute> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<PrivateKeyTemplateAttribute> type() {
        return PrivateKeyTemplateAttribute.class;
    }

    @Override
    protected PrivateKeyTemplateAttribute createDefault() {
        return PrivateKeyTemplateAttribute.of("default-string");  // TODO: Create a default instance
    }

    @Override
    protected PrivateKeyTemplateAttribute createVariant() {
        return PrivateKeyTemplateAttribute.of("variant-string");  // TODO: Create a variant instance
    }
}