package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RevokeOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RevokeOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<RevokeOpResponsePayload, RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder> {

    public RevokeOpResponsePayloadTtlvDeserializer() {
        super(RevokeOpResponsePayload.kmipTag);
    }

    @Override
    protected RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder createBuilder() {
        return RevokeOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
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

    @Override
    protected EncodingType getEncodingType() {
        return RevokeOpResponsePayload.encodingType;
    }
}
