package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyValueLocationType, Integer> {

    public KeyValueLocationTypeTtlvDeserializer() {
        super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType, Integer.class, value -> new KeyValueLocationType(KeyValueLocationType.fromValue(value)));
    }
}