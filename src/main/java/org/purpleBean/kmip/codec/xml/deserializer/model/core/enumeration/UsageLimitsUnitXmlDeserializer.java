package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<UsageLimitsUnit, String> {

    public UsageLimitsUnitXmlDeserializer() {
        super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType, String.class, value -> UsageLimitsUnit.fromName(value).inst());
    }
}