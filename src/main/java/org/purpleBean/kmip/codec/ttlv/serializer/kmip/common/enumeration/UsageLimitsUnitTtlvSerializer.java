package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UsageLimitsUnit, Integer> {

    public UsageLimitsUnitTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}