package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyFormatType, String> {

    public KeyFormatTypeXmlSerializer() {
        super(KeyFormatType::getDescription);
    }
}