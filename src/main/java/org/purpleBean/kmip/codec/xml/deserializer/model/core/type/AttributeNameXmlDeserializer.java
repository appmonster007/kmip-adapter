package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.io.IOException;

public class AttributeNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeName, AttributeName.AttributeNameBuilder> {

    public AttributeNameXmlDeserializer() {
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