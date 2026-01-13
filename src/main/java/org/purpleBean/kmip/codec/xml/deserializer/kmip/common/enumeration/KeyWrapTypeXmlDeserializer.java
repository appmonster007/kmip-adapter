package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyWrapType, String> {

    public KeyWrapTypeXmlDeserializer() {
        super(KeyWrapType.kmipTag, KeyWrapType.encodingType, String.class, value -> new KeyWrapType(KeyWrapType.fromName(value)));
    }
}