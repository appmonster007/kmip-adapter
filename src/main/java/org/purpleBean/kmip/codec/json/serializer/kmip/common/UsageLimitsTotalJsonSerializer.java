package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

public class UsageLimitsTotalJsonSerializer extends AbstractKmipJsonSerializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalJsonSerializer() {
        super(UsageLimitsTotal::getValue);
    }
}