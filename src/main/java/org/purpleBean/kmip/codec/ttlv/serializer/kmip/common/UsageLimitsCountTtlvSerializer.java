package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.UsageLimitsCount;

public class UsageLimitsCountTtlvSerializer extends AbstractKmipTtlvSerializer<UsageLimitsCount, Long> {

    public UsageLimitsCountTtlvSerializer() {
        super(UsageLimitsCount::getValue);
    }
}