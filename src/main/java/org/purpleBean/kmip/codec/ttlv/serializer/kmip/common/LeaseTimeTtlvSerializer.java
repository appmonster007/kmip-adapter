package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<LeaseTime, Integer> {

    public LeaseTimeTtlvSerializer() {
        super(LeaseTime::getValue);
    }
}