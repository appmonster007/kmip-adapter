package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;

import java.io.IOException;

public class ReplacedUniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ReplacedUniqueIdentifier, ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder> {

    public ReplacedUniqueIdentifierJsonDeserializer() {
        super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType);
    }

    @Override
    protected ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder createBuilder() {
        return ReplacedUniqueIdentifier.builder();
    }

    @Override
    protected void setValue(ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected ReplacedUniqueIdentifier build(ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder) {
        return builder.build();
    }
}
