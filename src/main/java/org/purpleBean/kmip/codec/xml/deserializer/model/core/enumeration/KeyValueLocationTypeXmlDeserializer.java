package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;

import java.io.IOException;

public class KeyValueLocationTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyValueLocationType, KeyValueLocationType.KeyValueLocationTypeBuilder> {

    public KeyValueLocationTypeXmlDeserializer() {
        super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType);
    }

    @Override
    protected KeyValueLocationType.KeyValueLocationTypeBuilder createBuilder() {
        return KeyValueLocationType.builder();
    }

    @Override
    protected void setValue(KeyValueLocationType.KeyValueLocationTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(KeyValueLocationType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected KeyValueLocationType build(KeyValueLocationType.KeyValueLocationTypeBuilder builder) {
        return builder.build();
    }
}