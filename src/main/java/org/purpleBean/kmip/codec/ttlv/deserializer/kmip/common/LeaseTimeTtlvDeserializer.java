package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeTtlvDeserializer extends AbstractKmipTtlvDeserializer<LeaseTime, Integer> {

    public LeaseTimeTtlvDeserializer() {
        super(LeaseTime.kmipTag, LeaseTime.encodingType, Integer.class, value -> LeaseTime.builder().value(value).build());
    }
}