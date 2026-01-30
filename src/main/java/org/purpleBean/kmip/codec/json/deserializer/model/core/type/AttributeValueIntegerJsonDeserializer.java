package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

import java.io.IOException;

public class AttributeValueIntegerJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueInteger, AttributeValueInteger.AttributeValueIntegerBuilder> {

    public AttributeValueIntegerJsonDeserializer() {
        super(AttributeValueInteger.kmipTag, AttributeValueInteger.encodingType);
    }

    @Override
    protected AttributeValueInteger.AttributeValueIntegerBuilder createBuilder() {
        return AttributeValueInteger.builder();
    }

    @Override
    protected void setValue(AttributeValueInteger.AttributeValueIntegerBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected AttributeValueInteger build(AttributeValueInteger.AttributeValueIntegerBuilder builder) {
        return builder.build();
    }
}
