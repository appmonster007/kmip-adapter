package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.io.IOException;

public class UniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UniqueIdentifier, UniqueIdentifier.UniqueIdentifierBuilder> {

    public UniqueIdentifierJsonDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType);
    }

    @Override
    protected UniqueIdentifier.UniqueIdentifierBuilder createBuilder() {
        return UniqueIdentifier.builder();
    }

    @Override
    protected void setValue(UniqueIdentifier.UniqueIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected UniqueIdentifier build(UniqueIdentifier.UniqueIdentifierBuilder builder) {
        return builder.build();
    }
}
