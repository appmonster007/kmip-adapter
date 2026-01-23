package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.NotifyOpRequestPayload;

import java.io.IOException;

public class NotifyOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<NotifyOpRequestPayload, NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder> {

    public NotifyOpRequestPayloadJsonDeserializer() {
        super(NotifyOpRequestPayload.kmipTag, NotifyOpRequestPayload.encodingType);
    }

    @Override
    protected NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder createBuilder() {
        return NotifyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected NotifyOpRequestPayload build(NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
