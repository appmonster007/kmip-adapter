package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeXmlDeserializer extends AbstractKmipXmlDeserializer<KeyValueLocationType, String> {

    public KeyValueLocationTypeXmlDeserializer() {
        super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType, String.class, value -> new KeyValueLocationType(KeyValueLocationType.fromName(value)));
    }
}