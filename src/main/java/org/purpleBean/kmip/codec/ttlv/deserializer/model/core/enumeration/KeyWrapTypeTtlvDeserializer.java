package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;

public class KeyWrapTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyWrapType, Integer> {

    public KeyWrapTypeTtlvDeserializer() {
        super(KeyWrapType.kmipTag, KeyWrapType.encodingType, Integer.class, value -> KeyWrapType.fromValue(value).inst());
    }
}