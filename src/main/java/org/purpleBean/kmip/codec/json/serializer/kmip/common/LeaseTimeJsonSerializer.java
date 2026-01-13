package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<LeaseTime, Integer> {

    public LeaseTimeJsonSerializer() {
        super(LeaseTime::getValue);
    }
}