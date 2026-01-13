package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitXmlSerializer() {
        super(UsageLimitsUnit::getDescription);
    }
}