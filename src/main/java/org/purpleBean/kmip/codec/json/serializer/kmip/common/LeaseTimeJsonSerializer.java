package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeJsonSerializer extends AbstractKmipJsonSerializer<LeaseTime, Integer> {

    public LeaseTimeJsonSerializer() {
        super(LeaseTime::getValue);
    }
}