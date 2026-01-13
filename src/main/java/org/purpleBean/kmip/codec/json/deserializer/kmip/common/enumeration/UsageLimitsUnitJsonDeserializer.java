package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitJsonDeserializer() {
        super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType, String.class, value -> new UsageLimitsUnit(UsageLimitsUnit.fromName(value)));
    }
}