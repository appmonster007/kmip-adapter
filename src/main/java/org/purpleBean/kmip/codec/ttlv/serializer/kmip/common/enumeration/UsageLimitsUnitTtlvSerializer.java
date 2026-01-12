package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitTtlvSerializer extends AbstractKmipTtlvSerializer<UsageLimitsUnit, Integer> {

    public UsageLimitsUnitTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}