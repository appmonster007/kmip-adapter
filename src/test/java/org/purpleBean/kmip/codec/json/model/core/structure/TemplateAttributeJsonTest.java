package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.model.core.structure.Name;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TemplateAttribute Json Serialization Tests")
class TemplateAttributeJsonTest extends AbstractJsonSerializationTestSuite<TemplateAttribute> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<TemplateAttribute> type() {
        return TemplateAttribute.class;
    }

    @Override
    protected TemplateAttribute createDefault() {
        return TemplateAttribute.builder()
                .name(Name.of(
                        NameValue.of("test-name"),
                        NameType.Standard.UNINTERPRETED_TEXT_STRING.inst()
                ))
                .build();
    }

    @Override
    protected TemplateAttribute createVariant() {
        return TemplateAttribute.builder()
                .name(Name.of(
                        NameValue.of("test-name-2"),
                        NameType.Standard.UNINTERPRETED_TEXT_STRING.inst()
                ))
                .build();
    }
}