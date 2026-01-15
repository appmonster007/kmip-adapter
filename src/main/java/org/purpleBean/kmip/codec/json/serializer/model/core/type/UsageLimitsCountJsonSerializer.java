package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;

public class UsageLimitsCountJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UsageLimitsCount, Long> {

    public UsageLimitsCountJsonSerializer() {
        super(UsageLimitsCount::getValue);
    }
}