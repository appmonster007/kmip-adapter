package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.AttributeValue;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CustomAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.io.IOException;

public class CustomAttributeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CustomAttribute, CustomAttribute.CustomAttributeBuilder> {

    public CustomAttributeJsonDeserializer() {
        super(CustomAttribute.kmipTag, CustomAttribute.encodingType);
    }

    @Override
    protected CustomAttribute.CustomAttributeBuilder createBuilder() {
        return CustomAttribute.builder();
    }

    @Override
    protected void setValue(CustomAttribute.CustomAttributeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
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
