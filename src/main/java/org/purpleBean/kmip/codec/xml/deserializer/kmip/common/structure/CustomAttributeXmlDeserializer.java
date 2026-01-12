package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.AttributeValue;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.structure.CustomAttribute;

import java.io.IOException;

public class CustomAttributeXmlDeserializer extends AbstractKmipStructureXmlDeserializer<CustomAttribute, CustomAttribute.CustomAttributeBuilder> {

    public CustomAttributeXmlDeserializer() {
        super(CustomAttribute.kmipTag);
    }

    @Override
    protected CustomAttribute.CustomAttributeBuilder createBuilder() {
        return CustomAttribute.builder();
    }

    @Override
    protected void setValue(CustomAttribute.CustomAttributeBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(ctxt.readValue(p, AttributeName.class));
            case KmipTag.Standard.ATTRIBUTE_VALUE -> builder.attributeValue(ctxt.readValue(p, AttributeValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CustomAttribute build(CustomAttribute.CustomAttributeBuilder builder) {
        return builder.build();
    }
}