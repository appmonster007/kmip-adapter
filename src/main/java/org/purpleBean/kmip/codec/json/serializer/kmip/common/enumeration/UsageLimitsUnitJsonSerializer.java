package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitJsonSerializer extends AbstractKmipJsonSerializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitJsonSerializer() {
        super(UsageLimitsUnit::getDescription);
    }
}