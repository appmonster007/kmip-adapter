package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;

public class UsageLimitsTotalTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalTtlvDeserializer() {
        super(UsageLimitsTotal.kmipTag, UsageLimitsTotal.encodingType, Long.class, value -> UsageLimitsTotal.builder().value(value).build());
    }
}