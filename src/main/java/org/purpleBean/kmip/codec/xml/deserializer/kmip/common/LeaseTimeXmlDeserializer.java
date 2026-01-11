package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeXmlDeserializer extends AbstractKmipXmlDeserializer<LeaseTime, Integer> {

    public LeaseTimeXmlDeserializer() {
        super(LeaseTime.kmipTag, LeaseTime.encodingType, Integer.class, value -> LeaseTime.builder().value(value).build());
    }
}