package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyValueLocationType, String> {

    public KeyValueLocationTypeXmlDeserializer() {
        super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType, String.class, value -> new KeyValueLocationType(KeyValueLocationType.fromName(value)));
    }
}