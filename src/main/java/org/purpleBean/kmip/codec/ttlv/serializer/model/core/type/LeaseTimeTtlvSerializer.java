package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.LeaseTime;

public class LeaseTimeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<LeaseTime, Integer> {

    public LeaseTimeTtlvSerializer() {
        super(LeaseTime::getValue);
    }
}