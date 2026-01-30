package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RevokeOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RevokeOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RevokeOpResponsePayload, RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder> {

    public RevokeOpResponsePayloadTtlvDeserializer() {
        super(RevokeOpResponsePayload.kmipTag, RevokeOpResponsePayload.encodingType);
    }

    @Override
    protected RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder createBuilder() {
        return RevokeOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RevokeOpResponsePayload build(RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
