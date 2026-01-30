package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBigInteger;

import java.io.IOException;
import java.math.BigInteger;

public class AttributeValueBigIntegerJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueBigInteger, AttributeValueBigInteger.AttributeValueBigIntegerBuilder> {

    public AttributeValueBigIntegerJsonDeserializer() {
        super(AttributeValueBigInteger.kmipTag, AttributeValueBigInteger.encodingType);
    }

    @Override
    protected AttributeValueBigInteger.AttributeValueBigIntegerBuilder createBuilder() {
        return AttributeValueBigInteger.builder();
    }

    @Override
    protected void setValue(AttributeValueBigInteger.AttributeValueBigIntegerBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, BigInteger.class));
    }

    @Override
    protected AttributeValueBigInteger build(AttributeValueBigInteger.AttributeValueBigIntegerBuilder builder) {
        return builder.build();
    }
}
