package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.AttributeValue;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.io.IOException;

public class AttributeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Attribute, Attribute.AttributeBuilder> {

    public AttributeJsonDeserializer() {
        super(Attribute.kmipTag, Attribute.encodingType);
    }

    @Override
    protected Attribute.AttributeBuilder createBuilder() {
        return Attribute.builder();
    }

    @Override
    protected void setValue(Attribute.AttributeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
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
