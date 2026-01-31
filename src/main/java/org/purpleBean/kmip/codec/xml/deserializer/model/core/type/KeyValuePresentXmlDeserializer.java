package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.KeyValuePresent;

import java.io.IOException;

public class KeyValuePresentXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyValuePresent, KeyValuePresent.KeyValuePresentBuilder> {

    public KeyValuePresentXmlDeserializer() {
        super(KeyValuePresent.kmipTag, KeyValuePresent.encodingType);
    }

    @Override
    protected KeyValuePresent.KeyValuePresentBuilder createBuilder() {
        return KeyValuePresent.builder();
    }

    @Override
    protected void setValue(KeyValuePresent.KeyValuePresentBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected KeyValuePresent build(KeyValuePresent.KeyValuePresentBuilder builder) {
        return builder.build();
    }
}