package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitJsonSerializer() {
        super(UsageLimitsUnit::getDescription);
    }
}