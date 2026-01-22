package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RecoverOpRequestPayload;

import java.io.IOException;

public class RecoverOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<RecoverOpRequestPayload, RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder> {

    public RecoverOpRequestPayloadJsonDeserializer() {
        super(RecoverOpRequestPayload.kmipTag, RecoverOpRequestPayload.encodingType);
    }

    @Override
    protected RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder createBuilder() {
        return RecoverOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RecoverOpRequestPayload build(RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
