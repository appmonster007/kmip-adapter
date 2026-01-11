package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeJsonDeserializer extends AbstractKmipJsonDeserializer<KeyWrapType, String> {

    public KeyWrapTypeJsonDeserializer() {
        super(KeyWrapType.kmipTag, KeyWrapType.encodingType, String.class, value -> new KeyWrapType(KeyWrapType.fromName(value)));
    }
}