package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.common.structure.UsageLimits;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UsageLimitsTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<UsageLimits, UsageLimits.UsageLimitsBuilder> {

    public UsageLimitsTtlvDeserializer() {
        super(UsageLimits.kmipTag);
    }

    @Override
    protected UsageLimits.UsageLimitsBuilder createBuilder() {
        return UsageLimits.builder();
    }

    @Override
    protected void setValue(UsageLimits.UsageLimitsBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
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

    @Override
    protected EncodingType getEncodingType() {
        return UsageLimits.encodingType;
    }
}