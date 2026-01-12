package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.LeaseTime;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class LeaseTimeXmlSerializer extends AbstractKmipXmlSerializer<LeaseTime, Integer> {

    public LeaseTimeXmlSerializer() {
        super(LeaseTime::getValue);
    }
}