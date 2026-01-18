package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.LeaseTime;

public class LeaseTimeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LeaseTime, Integer> {

    public LeaseTimeXmlDeserializer() {
        super(LeaseTime.kmipTag, LeaseTime.encodingType, Integer.class, value -> LeaseTime.builder().value(value).build());
    }
}