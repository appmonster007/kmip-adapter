package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitJsonDeserializer() {
        super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType, String.class, value -> UsageLimitsUnit.fromName(value).inst());
    }
}