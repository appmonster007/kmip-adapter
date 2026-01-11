package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeXmlDeserializer extends AbstractKmipXmlDeserializer<KeyWrapType, String> {

    public KeyWrapTypeXmlDeserializer() {
        super(KeyWrapType.kmipTag, KeyWrapType.encodingType, String.class, value -> new KeyWrapType(KeyWrapType.fromName(value)));
    }
}