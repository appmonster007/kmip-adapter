package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInterval;

import java.io.IOException;

public class AttributeValueIntervalXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueInterval, AttributeValueInterval.AttributeValueIntervalBuilder> {

    public AttributeValueIntervalXmlDeserializer() {
        super(AttributeValueInterval.kmipTag, AttributeValueInterval.encodingType);
    }

    @Override
    protected AttributeValueInterval.AttributeValueIntervalBuilder createBuilder() {
        return AttributeValueInterval.builder();
    }

    @Override
    protected void setValue(AttributeValueInterval.AttributeValueIntervalBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected AttributeValueInterval build(AttributeValueInterval.AttributeValueIntervalBuilder builder) {
        return builder.build();
    }
}