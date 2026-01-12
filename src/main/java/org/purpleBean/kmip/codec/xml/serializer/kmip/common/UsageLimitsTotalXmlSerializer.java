package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class UsageLimitsTotalXmlSerializer extends AbstractKmipXmlSerializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalXmlSerializer() {
        super(UsageLimitsTotal::getValue);
    }
}