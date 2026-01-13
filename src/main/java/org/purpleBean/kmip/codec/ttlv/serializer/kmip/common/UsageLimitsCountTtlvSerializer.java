package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.UsageLimitsCount;

public class UsageLimitsCountTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UsageLimitsCount, Long> {

    public UsageLimitsCountTtlvSerializer() {
        super(UsageLimitsCount::getValue);
    }
}