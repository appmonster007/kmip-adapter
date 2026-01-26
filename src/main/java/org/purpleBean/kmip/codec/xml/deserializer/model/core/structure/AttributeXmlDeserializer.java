package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.AttributeValue;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.io.IOException;

public class AttributeXmlDeserializer extends AbstractKmipStructureXmlDeserializer<Attribute, Attribute.AttributeBuilder> {

    public AttributeXmlDeserializer() {
        super(Attribute.kmipTag);
    }

    @Override
    protected Attribute.AttributeBuilder createBuilder() {
        return Attribute.builder();
    }

    @Override
    protected void setValue(Attribute.AttributeBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE_NAME -> {
                AttributeName attributeName = ctxt.readValue(p, AttributeName.class);
                ctxt.setAttribute("attributeName", attributeName.getValue());
                builder.attributeName(attributeName);
            }
            case KmipTag.Standard.ATTRIBUTE_INDEX -> builder.attributeIndex(ctxt.readValue(p, AttributeIndex.class));
            case KmipTag.Standard.ATTRIBUTE_VALUE -> builder.attributeValue(ctxt.readValue(p, AttributeValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Attribute build(Attribute.AttributeBuilder builder) {
        return builder.build();
    }
}