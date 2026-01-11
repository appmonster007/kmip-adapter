package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitXmlDeserializer extends AbstractKmipXmlDeserializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitXmlDeserializer() {
        super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType, String.class, value -> new UsageLimitsUnit(UsageLimitsUnit.fromName(value)));
    }
}