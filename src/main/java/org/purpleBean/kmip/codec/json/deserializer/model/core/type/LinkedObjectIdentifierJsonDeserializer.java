package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;

import java.io.IOException;

public class LinkedObjectIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<LinkedObjectIdentifier, LinkedObjectIdentifier.LinkedObjectIdentifierBuilder> {

    public LinkedObjectIdentifierJsonDeserializer() {
        super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType);
    }

    @Override
    protected LinkedObjectIdentifier.LinkedObjectIdentifierBuilder createBuilder() {
        return LinkedObjectIdentifier.builder();
    }

    @Override
    protected void setValue(LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected LinkedObjectIdentifier build(LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder) {
        return builder.build();
    }
}
