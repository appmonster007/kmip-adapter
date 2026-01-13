package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

public class UsageLimitsTotalJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalJsonSerializer() {
        super(UsageLimitsTotal::getValue);
    }
}