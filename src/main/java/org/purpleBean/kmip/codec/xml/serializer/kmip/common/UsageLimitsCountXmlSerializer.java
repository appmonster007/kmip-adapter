package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.UsageLimitsCount;

public class UsageLimitsCountXmlSerializer extends AbstractKmipXmlSerializer<UsageLimitsCount, Long> {

    public UsageLimitsCountXmlSerializer() {
        super(UsageLimitsCount::getValue);
    }
}