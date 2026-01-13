package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueJsonDeserializer() {
        super(KeyValueLocationValue.kmipTag, KeyValueLocationValue.encodingType, String.class, value -> KeyValueLocationValue.builder().value(value).build());
    }
}