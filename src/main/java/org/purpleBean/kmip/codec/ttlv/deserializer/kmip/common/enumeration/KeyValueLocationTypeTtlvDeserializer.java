package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyValueLocationType, Integer> {

    public KeyValueLocationTypeTtlvDeserializer() {
        super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType, Integer.class, value -> new KeyValueLocationType(KeyValueLocationType.fromValue(value)));
    }
}