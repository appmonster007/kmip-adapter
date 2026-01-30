package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetUsageAllocationOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetUsageAllocationOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<GetUsageAllocationOpResponsePayload, GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder> {

    public GetUsageAllocationOpResponsePayloadTtlvDeserializer() {
        super(GetUsageAllocationOpResponsePayload.kmipTag, GetUsageAllocationOpResponsePayload.encodingType);
    }

    @Override
    protected GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder createBuilder() {
        return GetUsageAllocationOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetUsageAllocationOpResponsePayload build(GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
