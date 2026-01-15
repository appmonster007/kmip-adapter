package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitXmlSerializer() {
        super(UsageLimitsUnit::getDescription);
    }
}