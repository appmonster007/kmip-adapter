package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;

public class UsageLimitsCountXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<UsageLimitsCount, Long> {

    public UsageLimitsCountXmlDeserializer() {
        super(UsageLimitsCount.kmipTag, UsageLimitsCount.encodingType, Long.class, value -> UsageLimitsCount.builder().value(value).build());
    }
}