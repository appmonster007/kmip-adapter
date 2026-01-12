package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentTtlvDeserializer extends AbstractKmipTtlvDeserializer<KeyValuePresent, Boolean> {

    public KeyValuePresentTtlvDeserializer() {
        super(KeyValuePresent.kmipTag, KeyValuePresent.encodingType, Boolean.class, value -> KeyValuePresent.builder().value(value).build());
    }
}