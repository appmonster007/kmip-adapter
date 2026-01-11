package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeXmlDeserializer extends AbstractKmipXmlDeserializer<KeyFormatType, String> {

    public KeyFormatTypeXmlDeserializer() {
        super(KeyFormatType.kmipTag, KeyFormatType.encodingType, String.class, value -> new KeyFormatType(KeyFormatType.fromName(value)));
    }
}