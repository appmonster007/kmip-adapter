package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetUsageAllocationOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetUsageAllocationOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<GetUsageAllocationOpRequestPayload, GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder> {

    public GetUsageAllocationOpRequestPayloadTtlvDeserializer() {
        super(GetUsageAllocationOpRequestPayload.kmipTag, GetUsageAllocationOpRequestPayload.encodingType);
    }

    @Override
    protected GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder createBuilder() {
        return GetUsageAllocationOpRequestPayload.builder();
    }

    @Override
    protected void setValue(GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.USAGE_LIMITS_COUNT ->
                    builder.usageLimitsCount(mapper.readValue(p, UsageLimitsCount.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetUsageAllocationOpRequestPayload build(GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
