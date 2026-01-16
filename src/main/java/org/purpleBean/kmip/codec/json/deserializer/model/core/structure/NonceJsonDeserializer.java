package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.io.IOException;

public class NonceJsonDeserializer extends AbstractKmipStructureJsonDeserializer<Nonce, Nonce.NonceBuilder> {

    public NonceJsonDeserializer() {
        super(Nonce.kmipTag, Nonce.encodingType);
    }

    @Override
    protected Nonce.NonceBuilder createBuilder() {
        return Nonce.builder();
    }

    @Override
    protected void setValue(Nonce.NonceBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.NONCE_ID -> builder.nonceId(ctxt.readValue(p, NonceId.class));
            case KmipTag.Standard.NONCE_VALUE -> builder.nonceValue(ctxt.readValue(p, NonceValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Nonce build(Nonce.NonceBuilder builder) {
        return builder.build();
    }
}