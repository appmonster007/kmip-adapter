package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyFormatType, String> {

    public KeyFormatTypeXmlSerializer() {
        super(KeyFormatType::getDescription);
    }
}