package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.KeyValuePresent;

import java.io.IOException;

public class KeyValuePresentJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyValuePresent, KeyValuePresent.KeyValuePresentBuilder> {

    public KeyValuePresentJsonDeserializer() {
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
