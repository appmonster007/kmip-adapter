package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CheckOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CheckOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CheckOpRequestPayload, CheckOpRequestPayload.CheckOpRequestPayloadBuilder> {

    public CheckOpRequestPayloadTtlvDeserializer() {
        super(CheckOpRequestPayload.kmipTag, CheckOpRequestPayload.encodingType);
    }

    @Override
    protected CheckOpRequestPayload.CheckOpRequestPayloadBuilder createBuilder() {
        return CheckOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CheckOpRequestPayload.CheckOpRequestPayloadBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.USAGE_LIMITS_COUNT ->
                    builder.usageLimitsCount(mapper.readValue(p, UsageLimitsCount.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_USAGE_MASK ->
                    builder.cryptographicUsageMask(mapper.readValue(p, CryptographicUsageMask.class));
            case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(mapper.readValue(p, LeaseTime.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CheckOpRequestPayload build(CheckOpRequestPayload.CheckOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
