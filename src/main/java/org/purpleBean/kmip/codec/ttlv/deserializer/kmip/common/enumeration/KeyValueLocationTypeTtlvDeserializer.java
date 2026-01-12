package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<KeyValueLocationType, Integer> {

    public KeyValueLocationTypeTtlvDeserializer() {
        super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType, Integer.class, value -> new KeyValueLocationType(KeyValueLocationType.fromValue(value)));
    }
}