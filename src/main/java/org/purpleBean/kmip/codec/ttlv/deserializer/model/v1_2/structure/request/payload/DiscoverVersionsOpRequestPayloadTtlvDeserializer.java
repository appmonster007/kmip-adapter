package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DiscoverVersionsOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DiscoverVersionsOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DiscoverVersionsOpRequestPayload, DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder> {

    public DiscoverVersionsOpRequestPayloadTtlvDeserializer() {
        super(DiscoverVersionsOpRequestPayload.kmipTag, DiscoverVersionsOpRequestPayload.encodingType);
    }

    @Override
    protected DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder createBuilder() {
        return DiscoverVersionsOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
}
