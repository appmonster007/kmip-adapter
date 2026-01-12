package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.UsageLimitsCount;

public class UsageLimitsCountTtlvDeserializer extends AbstractKmipTtlvDeserializer<UsageLimitsCount, Long> {

    public UsageLimitsCountTtlvDeserializer() {
        super(UsageLimitsCount.kmipTag, UsageLimitsCount.encodingType, Long.class, value -> UsageLimitsCount.builder().value(value).build());
    }
}