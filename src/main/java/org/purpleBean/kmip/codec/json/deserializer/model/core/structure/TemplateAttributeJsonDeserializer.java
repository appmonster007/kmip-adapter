package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Name;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;

import java.io.IOException;

public class TemplateAttributeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<TemplateAttribute, TemplateAttribute.TemplateAttributeBuilder> {

    public TemplateAttributeJsonDeserializer() {
        super(TemplateAttribute.kmipTag, TemplateAttribute.encodingType);
    }

    @Override
    protected TemplateAttribute.TemplateAttributeBuilder createBuilder() {
        return TemplateAttribute.builder();
    }

    @Override
    protected void setValue(TemplateAttribute.TemplateAttributeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.NAME -> builder.name(ctxt.readValue(p, Name.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TemplateAttribute build(TemplateAttribute.TemplateAttributeBuilder builder) {
        return builder.build();
    }
}