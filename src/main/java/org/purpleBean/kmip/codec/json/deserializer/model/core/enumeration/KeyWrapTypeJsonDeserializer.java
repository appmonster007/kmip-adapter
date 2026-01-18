package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;

public class KeyWrapTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyWrapType, String> {

    public KeyWrapTypeJsonDeserializer() {
        super(KeyWrapType.kmipTag, KeyWrapType.encodingType, String.class, value -> new KeyWrapType(KeyWrapType.fromName(value)));
    }
}