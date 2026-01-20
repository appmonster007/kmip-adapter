package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PublicKeyTemplateAttribute Ttlv Serialization Tests")
class PublicKeyTemplateAttributeTtlvTest extends AbstractTtlvSerializationTestSuite<PublicKeyTemplateAttribute> {

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
        return PublicKeyTemplateAttribute.of("default-string");  // TODO: Create a default instance
    }

    @Override
    protected PublicKeyTemplateAttribute createVariant() {
        return PublicKeyTemplateAttribute.of("variant-string");  // TODO: Create a variant instance
    }
}