package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.AttributeValue;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.AttributeIndex;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;

public class AttributeJsonDeserializer extends AbstractKmipStructureJsonDeserializer<Attribute, Attribute.AttributeBuilder> {

    public AttributeJsonDeserializer() {
        super(Attribute.kmipTag, Attribute.encodingType);
    }

    @Override
    protected Attribute.AttributeBuilder createBuilder() {
        return Attribute.builder();
    }

    @Override
    protected void setValue(Attribute.AttributeBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(ctxt.readValue(p, AttributeName.class));
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