package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;

public class UsageLimitsTotalJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UsageLimitsTotal, Long> {

    public UsageLimitsTotalJsonSerializer() {
        super(UsageLimitsTotal::getValue);
    }
}