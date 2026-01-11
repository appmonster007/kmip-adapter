package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitJsonDeserializer extends AbstractKmipJsonDeserializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitJsonDeserializer() {
        super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType, String.class, value -> new UsageLimitsUnit(UsageLimitsUnit.fromName(value)));
    }
}