package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DestroyOpRequestPayload;

import java.io.IOException;

public class DestroyOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<DestroyOpRequestPayload, DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder> {

    public DestroyOpRequestPayloadJsonDeserializer() {
        super(DestroyOpRequestPayload.kmipTag, DestroyOpRequestPayload.encodingType);
    }

    @Override
    protected DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder createBuilder() {
        return DestroyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DestroyOpRequestPayload build(DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
