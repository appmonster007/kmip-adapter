package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.SerialNumber;

public class SerialNumberXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SerialNumber, String> {

    public SerialNumberXmlSerializer() {
        super(SerialNumber::getValue);
    }
}