package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentXmlDeserializer extends AbstractKmipXmlDeserializer<KeyValuePresent, Boolean> {

    public KeyValuePresentXmlDeserializer() {
        super(KeyValuePresent.kmipTag, KeyValuePresent.encodingType, Boolean.class, value -> KeyValuePresent.builder().value(value).build());
    }
}