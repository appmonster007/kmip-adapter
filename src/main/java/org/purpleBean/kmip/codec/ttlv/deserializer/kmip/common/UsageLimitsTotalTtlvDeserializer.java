package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

public class UsageLimitsTotalTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalTtlvDeserializer() {
        super(UsageLimitsTotal.kmipTag, UsageLimitsTotal.encodingType, Long.class, value -> UsageLimitsTotal.builder().value(value).build());
    }
}