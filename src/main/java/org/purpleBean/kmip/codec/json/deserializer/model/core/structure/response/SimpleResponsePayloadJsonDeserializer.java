package org.purpleBean.kmip.codec.json.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;

import java.io.IOException;

public class SimpleResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SimpleResponsePayload, SimpleResponsePayload.SimpleResponsePayloadBuilder> {

    public SimpleResponsePayloadJsonDeserializer() {
        super(SimpleResponsePayload.kmipTag, SimpleResponsePayload.encodingType);
    }

    @Override
    protected SimpleResponsePayload.SimpleResponsePayloadBuilder createBuilder() {
        return SimpleResponsePayload.builder();
    }

    @Override
    protected void setValue(SimpleResponsePayload.SimpleResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
// No fields to deserialize
    }

    @Override
    protected SimpleResponsePayload build(SimpleResponsePayload.SimpleResponsePayloadBuilder builder) {
        return builder.build();
    }
}
