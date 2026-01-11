package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

public class UsageLimitsTotalXmlDeserializer extends AbstractKmipXmlDeserializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalXmlDeserializer() {
        super(UsageLimitsTotal.kmipTag, UsageLimitsTotal.encodingType, Long.class, value -> UsageLimitsTotal.builder().value(value).build());
    }
}