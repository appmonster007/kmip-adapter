package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.UsageLimitsCount;

public class UsageLimitsCountXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UsageLimitsCount, Long> {

    public UsageLimitsCountXmlSerializer() {
        super(UsageLimitsCount::getValue);
    }
}