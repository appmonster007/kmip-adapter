package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

public class UsageLimitsTotalJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalJsonDeserializer() {
        super(UsageLimitsTotal.kmipTag, UsageLimitsTotal.encodingType, Long.class, value -> UsageLimitsTotal.builder().value(value).build());
    }
}