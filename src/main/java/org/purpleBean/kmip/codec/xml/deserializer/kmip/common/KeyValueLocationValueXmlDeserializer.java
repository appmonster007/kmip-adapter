package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueXmlDeserializer() {
        super(KeyValueLocationValue.kmipTag, KeyValueLocationValue.encodingType, String.class, value -> KeyValueLocationValue.builder().value(value).build());
    }
}