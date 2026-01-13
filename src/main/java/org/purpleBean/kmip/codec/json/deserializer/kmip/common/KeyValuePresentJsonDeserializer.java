package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyValuePresent, Boolean> {

    public KeyValuePresentJsonDeserializer() {
        super(KeyValuePresent.kmipTag, KeyValuePresent.encodingType, Boolean.class, value -> KeyValuePresent.builder().value(value).build());
    }
}