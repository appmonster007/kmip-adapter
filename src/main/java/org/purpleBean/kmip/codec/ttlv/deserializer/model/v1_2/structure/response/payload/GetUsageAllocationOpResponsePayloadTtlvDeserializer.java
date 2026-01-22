package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetUsageAllocationOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetUsageAllocationOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<GetUsageAllocationOpResponsePayload, GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder> {

    public GetUsageAllocationOpResponsePayloadTtlvDeserializer() {
        super(GetUsageAllocationOpResponsePayload.kmipTag);
    }

    @Override
    protected GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder createBuilder() {
        return GetUsageAllocationOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
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

    @Override
    protected EncodingType getEncodingType() {
        return GetUsageAllocationOpResponsePayload.encodingType;
    }
}
