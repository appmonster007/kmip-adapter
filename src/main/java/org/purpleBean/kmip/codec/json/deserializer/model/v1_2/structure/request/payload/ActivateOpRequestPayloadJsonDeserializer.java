package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ActivateOpRequestPayload;

import java.io.IOException;

public class ActivateOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ActivateOpRequestPayload, ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder> {

    public ActivateOpRequestPayloadJsonDeserializer() {
        super(ActivateOpRequestPayload.kmipTag, ActivateOpRequestPayload.encodingType);
    }

    @Override
    protected ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder createBuilder() {
        return ActivateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ActivateOpRequestPayload build(ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
