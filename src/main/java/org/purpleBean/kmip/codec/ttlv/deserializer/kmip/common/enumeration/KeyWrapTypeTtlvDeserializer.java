package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<KeyWrapType, Integer> {

    public KeyWrapTypeTtlvDeserializer() {
        super(KeyWrapType.kmipTag, KeyWrapType.encodingType, Integer.class, value -> new KeyWrapType(KeyWrapType.fromValue(value)));
    }
}