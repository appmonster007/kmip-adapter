package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;

public class UsageLimitsCountTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UsageLimitsCount, Long> {

    public UsageLimitsCountTtlvDeserializer() {
        super(UsageLimitsCount.kmipTag, UsageLimitsCount.encodingType, Long.class, value -> UsageLimitsCount.builder().value(value).build());
    }
}