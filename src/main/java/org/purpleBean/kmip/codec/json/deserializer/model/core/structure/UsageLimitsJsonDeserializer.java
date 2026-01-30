package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.model.core.structure.UsageLimits;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;

import java.io.IOException;

public class UsageLimitsJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UsageLimits, UsageLimits.UsageLimitsBuilder> {

    public UsageLimitsJsonDeserializer() {
        super(UsageLimits.kmipTag, UsageLimits.encodingType);
    }

    @Override
    protected UsageLimits.UsageLimitsBuilder createBuilder() {
        return UsageLimits.builder();
    }

    @Override
    protected void setValue(UsageLimits.UsageLimitsBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
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