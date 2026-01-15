package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.SerialNumber;

public class SerialNumberXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SerialNumber, String> {

    public SerialNumberXmlSerializer() {
        super(SerialNumber::getValue);
    }
}