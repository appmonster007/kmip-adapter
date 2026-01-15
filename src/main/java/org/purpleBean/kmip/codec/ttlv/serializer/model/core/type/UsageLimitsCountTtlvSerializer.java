package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;

public class UsageLimitsCountTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UsageLimitsCount, Long> {

    public UsageLimitsCountTtlvSerializer() {
        super(UsageLimitsCount::getValue);
    }
}