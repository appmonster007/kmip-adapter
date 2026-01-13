package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

public class KeyPartIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierXmlDeserializer() {
        super(KeyPartIdentifier.kmipTag, KeyPartIdentifier.encodingType, Integer.class, value -> KeyPartIdentifier.builder().value(value).build());
    }
}