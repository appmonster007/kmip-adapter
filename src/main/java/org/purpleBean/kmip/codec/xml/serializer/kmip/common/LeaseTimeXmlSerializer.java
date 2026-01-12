package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeXmlSerializer extends AbstractKmipXmlSerializer<LeaseTime, Integer> {

    public LeaseTimeXmlSerializer() {
        super(LeaseTime::getValue);
    }
}