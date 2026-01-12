package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

public class UsageLimitsTotalTtlvSerializer extends AbstractKmipTtlvSerializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalTtlvSerializer() {
        super(UsageLimitsTotal::getValue);
    }
}