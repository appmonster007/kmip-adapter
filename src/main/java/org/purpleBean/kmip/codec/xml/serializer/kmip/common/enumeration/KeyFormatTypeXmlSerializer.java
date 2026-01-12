package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeXmlSerializer extends AbstractKmipXmlSerializer<KeyFormatType, String> {

    public KeyFormatTypeXmlSerializer() {
        super(KeyFormatType::getDescription);
    }
}