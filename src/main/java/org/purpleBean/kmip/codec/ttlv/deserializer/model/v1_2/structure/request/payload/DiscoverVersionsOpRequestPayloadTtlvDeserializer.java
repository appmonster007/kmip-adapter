package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DiscoverVersionsOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DiscoverVersionsOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<DiscoverVersionsOpRequestPayload, DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder> {

    public DiscoverVersionsOpRequestPayloadTtlvDeserializer() {
        super(DiscoverVersionsOpRequestPayload.kmipTag);
    }

    @Override
    protected DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder createBuilder() {
        return DiscoverVersionsOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.PROTOCOL_VERSION)) {
            builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DiscoverVersionsOpRequestPayload build(DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return DiscoverVersionsOpRequestPayload.encodingType;
    }
}
