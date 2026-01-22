package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DiscoverVersionsOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DiscoverVersionsOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<DiscoverVersionsOpResponsePayload, DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder> {

    public DiscoverVersionsOpResponsePayloadTtlvDeserializer() {
        super(DiscoverVersionsOpResponsePayload.kmipTag);
    }

    @Override
    protected DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder createBuilder() {
        return DiscoverVersionsOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.PROTOCOL_VERSION)) {
            builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DiscoverVersionsOpResponsePayload build(DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return DiscoverVersionsOpResponsePayload.encodingType;
    }
}
