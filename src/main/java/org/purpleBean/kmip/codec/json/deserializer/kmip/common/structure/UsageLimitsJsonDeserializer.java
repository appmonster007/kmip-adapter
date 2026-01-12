package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.common.structure.UsageLimits;

import java.io.IOException;

public class UsageLimitsJsonDeserializer extends AbstractKmipStructureJsonDeserializer<UsageLimits, UsageLimits.UsageLimitsBuilder> {

    public UsageLimitsJsonDeserializer() {
        super(UsageLimits.kmipTag, UsageLimits.encodingType);
    }

    @Override
    protected UsageLimits.UsageLimitsBuilder createBuilder() {
        return UsageLimits.builder();
    }

    @Override
    protected void setValue(UsageLimits.UsageLimitsBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.USAGE_LIMITS_TOTAL ->
                    builder.usageLimitsTotal(ctxt.readValue(p, UsageLimitsTotal.class));
            case KmipTag.Standard.USAGE_LIMITS_COUNT ->
                    builder.usageLimitsCount(ctxt.readValue(p, UsageLimitsCount.class));
            case KmipTag.Standard.USAGE_LIMITS_UNIT ->
                    builder.usageLimitsUnit(ctxt.readValue(p, UsageLimitsUnit.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected UsageLimits build(UsageLimits.UsageLimitsBuilder builder) {
        return builder.build();
    }
}