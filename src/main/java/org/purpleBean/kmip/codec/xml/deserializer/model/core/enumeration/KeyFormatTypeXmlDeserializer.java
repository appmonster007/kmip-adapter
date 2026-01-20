package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyFormatType, String> {

    public KeyFormatTypeXmlDeserializer() {
        super(KeyFormatType.kmipTag, KeyFormatType.encodingType, String.class, value -> KeyFormatType.fromName(value).inst());
    }
}