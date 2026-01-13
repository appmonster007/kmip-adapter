package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyValueLocationType, String> {

    public KeyValueLocationTypeXmlDeserializer() {
        super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType, String.class, value -> new KeyValueLocationType(KeyValueLocationType.fromName(value)));
    }
}