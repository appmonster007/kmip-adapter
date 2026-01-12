package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitXmlSerializer extends AbstractKmipXmlSerializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitXmlSerializer() {
        super(UsageLimitsUnit::getDescription);
    }
}