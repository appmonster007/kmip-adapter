package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RngSeedOpResponsePayload;

import java.io.IOException;

public class RngSeedOpResponsePayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<RngSeedOpResponsePayload, RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder> {

    public RngSeedOpResponsePayloadJsonDeserializer() {
        super(RngSeedOpResponsePayload.kmipTag, RngSeedOpResponsePayload.encodingType);
    }

    @Override
    protected RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder createBuilder() {
        return RngSeedOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.DATA_LENGTH)) {
            builder.dataLength(ctxt.readValue(p, DataLength.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RngSeedOpResponsePayload build(RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
