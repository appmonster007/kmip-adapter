package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;

import java.io.IOException;

public class AttributeValueLongIntegerJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueLongInteger, AttributeValueLongInteger.AttributeValueLongIntegerBuilder> {

    public AttributeValueLongIntegerJsonDeserializer() {
        super(AttributeValueLongInteger.kmipTag, AttributeValueLongInteger.encodingType);
    }

    @Override
    protected AttributeValueLongInteger.AttributeValueLongIntegerBuilder createBuilder() {
        return AttributeValueLongInteger.builder();
    }

    @Override
    protected void setValue(AttributeValueLongInteger.AttributeValueLongIntegerBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Long.class));
    }

    @Override
    protected AttributeValueLongInteger build(AttributeValueLongInteger.AttributeValueLongIntegerBuilder builder) {
        return builder.build();
    }
}
