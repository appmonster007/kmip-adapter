package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

public class UsageLimitsTotalXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalXmlSerializer() {
        super(UsageLimitsTotal::getValue);
    }
}