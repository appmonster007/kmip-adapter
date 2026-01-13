package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueTtlvDeserializer() {
        super(KeyValueLocationValue.kmipTag, KeyValueLocationValue.encodingType, String.class, value -> KeyValueLocationValue.builder().value(value).build());
    }
}