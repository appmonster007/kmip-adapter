package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class SerialNumberXmlSerializer extends AbstractKmipXmlSerializer<SerialNumber, String> {

    public SerialNumberXmlSerializer() {
        super(SerialNumber::getValue);
    }
}