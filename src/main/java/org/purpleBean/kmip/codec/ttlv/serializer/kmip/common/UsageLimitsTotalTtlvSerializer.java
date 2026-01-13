package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

public class UsageLimitsTotalTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalTtlvSerializer() {
        super(UsageLimitsTotal::getValue);
    }
}