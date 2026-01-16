package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Template;

import java.io.IOException;

public class TemplateXmlDeserializer extends AbstractKmipStructureXmlDeserializer<Template, Template.TemplateBuilder> {

    public TemplateXmlDeserializer() {
        super(Template.kmipTag);
    }

    @Override
    protected Template.TemplateBuilder createBuilder() {
        return Template.builder();
    }

    @Override
    protected void setValue(Template.TemplateBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Template build(Template.TemplateBuilder builder) {
        return builder.build();
    }
}