package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.LeaseTime;

public class LeaseTimeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<LeaseTime, Integer> {

    public LeaseTimeJsonDeserializer() {
        super(LeaseTime.kmipTag, LeaseTime.encodingType, Integer.class, value -> LeaseTime.builder().value(value).build());
    }
}