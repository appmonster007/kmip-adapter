package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeTtlvSerializer extends AbstractKmipTtlvSerializer<LeaseTime, Integer> {

    public LeaseTimeTtlvSerializer() {
        super(LeaseTime::getValue);
    }
}