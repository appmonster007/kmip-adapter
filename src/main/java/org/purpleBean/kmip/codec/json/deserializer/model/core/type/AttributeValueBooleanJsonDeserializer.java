package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBoolean;

import java.io.IOException;

public class AttributeValueBooleanJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueBoolean, AttributeValueBoolean.AttributeValueBooleanBuilder> {

    public AttributeValueBooleanJsonDeserializer() {
        super(AttributeValueBoolean.kmipTag, AttributeValueBoolean.encodingType);
    }

    @Override
    protected AttributeValueBoolean.AttributeValueBooleanBuilder createBuilder() {
        return AttributeValueBoolean.builder();
    }

    @Override
    protected void setValue(AttributeValueBoolean.AttributeValueBooleanBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected AttributeValueBoolean build(AttributeValueBoolean.AttributeValueBooleanBuilder builder) {
        return builder.build();
    }
}
