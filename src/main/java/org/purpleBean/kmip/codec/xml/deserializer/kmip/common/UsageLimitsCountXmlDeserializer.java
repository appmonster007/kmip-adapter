package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.UsageLimitsCount;

public class UsageLimitsCountXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<UsageLimitsCount, Long> {

    public UsageLimitsCountXmlDeserializer() {
        super(UsageLimitsCount.kmipTag, UsageLimitsCount.encodingType, Long.class, value -> UsageLimitsCount.builder().value(value).build());
    }
}