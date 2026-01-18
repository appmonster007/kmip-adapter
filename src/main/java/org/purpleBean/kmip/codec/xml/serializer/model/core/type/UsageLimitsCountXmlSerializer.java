package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;

public class UsageLimitsCountXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UsageLimitsCount, Long> {

    public UsageLimitsCountXmlSerializer() {
        super(UsageLimitsCount::getValue);
    }
}