package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;

public class KeyCompressionTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyCompressionType, String> {

    public KeyCompressionTypeXmlDeserializer() {
        super(KeyCompressionType.kmipTag, KeyCompressionType.encodingType, String.class, value -> new KeyCompressionType(KeyCompressionType.fromName(value)));
    }
}