package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.Name;

import java.io.IOException;

public class CommonTemplateAttributeXmlDeserializer extends AbstractKmipStructureXmlDeserializer<CommonTemplateAttribute, CommonTemplateAttribute.CommonTemplateAttributeBuilder> {

    public CommonTemplateAttributeXmlDeserializer() {
        super(CommonTemplateAttribute.kmipTag);
    }

    @Override
    protected CommonTemplateAttribute.CommonTemplateAttributeBuilder createBuilder() {
        return CommonTemplateAttribute.builder();
    }

    @Override
    protected void setValue(CommonTemplateAttribute.CommonTemplateAttributeBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.NAME -> builder.name(ctxt.readValue(p, Name.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CommonTemplateAttribute build(CommonTemplateAttribute.CommonTemplateAttributeBuilder builder) {
        return builder.build();
    }
}