package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.model.core.structure.UsageLimits;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UsageLimitsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UsageLimits, UsageLimits.UsageLimitsBuilder> {

    public UsageLimitsTtlvDeserializer() {
        super(UsageLimits.kmipTag, UsageLimits.encodingType);
    }

    @Override
    protected UsageLimits.UsageLimitsBuilder createBuilder() {
        return UsageLimits.builder();
    }

    @Override
    protected void setValue(UsageLimits.UsageLimitsBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.USAGE_LIMITS_TOTAL ->
                    builder.usageLimitsTotal(mapper.readValue(p, UsageLimitsTotal.class));
            case KmipTag.Standard.USAGE_LIMITS_COUNT ->
                    builder.usageLimitsCount(mapper.readValue(p, UsageLimitsCount.class));
            case KmipTag.Standard.USAGE_LIMITS_UNIT ->
                    builder.usageLimitsUnit(mapper.readValue(p, UsageLimitsUnit.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected UsageLimits build(UsageLimits.UsageLimitsBuilder builder) {
        return builder.build();
    }
}