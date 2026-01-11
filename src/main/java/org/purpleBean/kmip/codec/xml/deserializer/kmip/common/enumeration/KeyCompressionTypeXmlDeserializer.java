package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

public class KeyCompressionTypeXmlDeserializer extends AbstractKmipXmlDeserializer<KeyCompressionType, String> {

    public KeyCompressionTypeXmlDeserializer() {
        super(KeyCompressionType.kmipTag, KeyCompressionType.encodingType, String.class, value -> new KeyCompressionType(KeyCompressionType.fromName(value)));
    }
}