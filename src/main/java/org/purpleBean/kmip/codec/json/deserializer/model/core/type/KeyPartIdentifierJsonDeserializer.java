package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;

import java.io.IOException;

public class KeyPartIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyPartIdentifier, KeyPartIdentifier.KeyPartIdentifierBuilder> {

    public KeyPartIdentifierJsonDeserializer() {
        super(KeyPartIdentifier.kmipTag, KeyPartIdentifier.encodingType);
    }

    @Override
    protected KeyPartIdentifier.KeyPartIdentifierBuilder createBuilder() {
        return KeyPartIdentifier.builder();
    }

    @Override
    protected void setValue(KeyPartIdentifier.KeyPartIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected KeyPartIdentifier build(KeyPartIdentifier.KeyPartIdentifierBuilder builder) {
        return builder.build();
    }
}
