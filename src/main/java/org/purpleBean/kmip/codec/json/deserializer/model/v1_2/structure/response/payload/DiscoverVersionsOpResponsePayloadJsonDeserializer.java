package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DiscoverVersionsOpResponsePayload;

import java.io.IOException;

public class DiscoverVersionsOpResponsePayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<DiscoverVersionsOpResponsePayload, DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder> {

    public DiscoverVersionsOpResponsePayloadJsonDeserializer() {
        super(DiscoverVersionsOpResponsePayload.kmipTag, DiscoverVersionsOpResponsePayload.encodingType);
    }

    @Override
    protected DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder createBuilder() {
        return DiscoverVersionsOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.PROTOCOL_VERSION)) {
            builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DiscoverVersionsOpResponsePayload build(DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
