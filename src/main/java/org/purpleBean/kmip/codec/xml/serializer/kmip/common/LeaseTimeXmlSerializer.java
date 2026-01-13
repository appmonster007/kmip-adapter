package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.LeaseTime;

public class LeaseTimeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<LeaseTime, Integer> {

    public LeaseTimeXmlSerializer() {
        super(LeaseTime::getValue);
    }
}