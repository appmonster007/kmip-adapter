package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Template;

import java.io.IOException;

public class TemplateJsonDeserializer extends AbstractKmipStructureJsonDeserializer<Template, Template.TemplateBuilder> {

    public TemplateJsonDeserializer() {
        super(Template.kmipTag, Template.encodingType);
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