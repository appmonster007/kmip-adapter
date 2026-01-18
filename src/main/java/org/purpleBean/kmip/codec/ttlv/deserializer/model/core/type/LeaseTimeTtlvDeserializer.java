package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.LeaseTime;

public class LeaseTimeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LeaseTime, Integer> {

    public LeaseTimeTtlvDeserializer() {
        super(LeaseTime.kmipTag, LeaseTime.encodingType, Integer.class, value -> LeaseTime.builder().value(value).build());
    }
}