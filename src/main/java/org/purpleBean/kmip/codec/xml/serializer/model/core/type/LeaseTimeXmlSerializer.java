package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.LeaseTime;

public class LeaseTimeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<LeaseTime, Integer> {

    public LeaseTimeXmlSerializer() {
        super(LeaseTime::getValue);
    }
}