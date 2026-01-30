package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.io.IOException;

public class AttributeNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeName, AttributeName.AttributeNameBuilder> {

    public AttributeNameJsonDeserializer() {
        super(AttributeName.kmipTag, AttributeName.encodingType);
    }

    @Override
    protected AttributeName.AttributeNameBuilder createBuilder() {
        return AttributeName.builder();
    }

    @Override
    protected void setValue(AttributeName.AttributeNameBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected AttributeName build(AttributeName.AttributeNameBuilder builder) {
        return builder.build();
    }
}
