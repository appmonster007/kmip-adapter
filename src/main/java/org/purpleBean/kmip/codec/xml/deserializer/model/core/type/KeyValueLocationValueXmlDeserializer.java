package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;

public class KeyValueLocationValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueXmlDeserializer() {
        super(KeyValueLocationValue.kmipTag, KeyValueLocationValue.encodingType, String.class, value -> KeyValueLocationValue.builder().value(value).build());
    }
}