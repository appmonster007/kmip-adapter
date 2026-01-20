package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PrivateKeyTemplateAttribute Xml Serialization Tests")
class PrivateKeyTemplateAttributeXmlTest extends AbstractXmlSerializationTestSuite<PrivateKeyTemplateAttribute> {

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