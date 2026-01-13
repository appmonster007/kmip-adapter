package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UsageLimitsUnit, Integer> {

    public UsageLimitsUnitTtlvDeserializer() {
        super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType, Integer.class, value -> new UsageLimitsUnit(UsageLimitsUnit.fromValue(value)));
    }
}