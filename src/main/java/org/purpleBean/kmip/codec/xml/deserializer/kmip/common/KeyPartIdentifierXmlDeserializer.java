package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

public class KeyPartIdentifierXmlDeserializer extends AbstractKmipXmlDeserializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierXmlDeserializer() {
        super(KeyPartIdentifier.kmipTag, KeyPartIdentifier.encodingType, Integer.class, value -> KeyPartIdentifier.builder().value(value).build());
    }
}