package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<LeaseTime, Integer> {

    public LeaseTimeJsonDeserializer() {
        super(LeaseTime.kmipTag, LeaseTime.encodingType, Integer.class, value -> LeaseTime.builder().value(value).build());
    }
}