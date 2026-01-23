package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.NotifyOpResponsePayload;

import java.io.IOException;

public class NotifyOpResponsePayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<NotifyOpResponsePayload, NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder> {

    public NotifyOpResponsePayloadJsonDeserializer() {
        super(NotifyOpResponsePayload.kmipTag, NotifyOpResponsePayload.encodingType);
    }

    @Override
    protected NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder createBuilder() {
        return NotifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        // No fields to deserialize
    }

    @Override
    protected NotifyOpResponsePayload build(NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
