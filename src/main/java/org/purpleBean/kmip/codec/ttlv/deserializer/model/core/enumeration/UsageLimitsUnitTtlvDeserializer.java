package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UsageLimitsUnit, Integer> {

    public UsageLimitsUnitTtlvDeserializer() {
        super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType, Integer.class, value -> new UsageLimitsUnit(UsageLimitsUnit.fromValue(value)));
    }
}