package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.model.core.structure.Name;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("TemplateAttribute Ttlv Serialization Tests")
class TemplateAttributeTtlvTest extends AbstractTtlvSerializationTestSuite<TemplateAttribute> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<TemplateAttribute> type() {
        return TemplateAttribute.class;
    }

    @Override
    public TemplateAttribute createDefault() {
        return TemplateAttribute.builder()
                .name(Name.of(
                        NameValue.of("test-name"),
                        NameType.Standard.UNINTERPRETED_TEXT_STRING.inst()
                ))
                .build();
    }

    @Override
    public TemplateAttribute createVariant() {
        return TemplateAttribute.builder()
                .name(Name.of(
                        NameValue.of("test-name-2"),
                        NameType.Standard.UNINTERPRETED_TEXT_STRING.inst()
                ))
                .build();
    }
}