package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UsageLimitsUnit, Integer> {

    public UsageLimitsUnitTtlvSerializer() {
        super(UsageLimitsUnit::getValue);
    }
}