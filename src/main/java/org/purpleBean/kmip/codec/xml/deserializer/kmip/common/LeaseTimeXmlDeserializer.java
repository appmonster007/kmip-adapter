package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LeaseTime, Integer> {

    public LeaseTimeXmlDeserializer() {
        super(LeaseTime.kmipTag, LeaseTime.encodingType, Integer.class, value -> LeaseTime.builder().value(value).build());
    }
}