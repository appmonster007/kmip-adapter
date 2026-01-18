package org.purpleBean.kmip.codec.json.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;

import java.io.IOException;

public class SimpleRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<SimpleRequestPayload, SimpleRequestPayload.SimpleRequestPayloadBuilder> {

    public SimpleRequestPayloadJsonDeserializer() {
        super(SimpleRequestPayload.kmipTag, SimpleRequestPayload.encodingType);
    }

    @Override
    protected SimpleRequestPayload.SimpleRequestPayloadBuilder createBuilder() {
        return SimpleRequestPayload.builder();
    }

    @Override
    protected void setValue(SimpleRequestPayload.SimpleRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        // No fields to set
    }

    @Override
    protected SimpleRequestPayload build(SimpleRequestPayload.SimpleRequestPayloadBuilder builder) {
        return builder.build();
    }
}