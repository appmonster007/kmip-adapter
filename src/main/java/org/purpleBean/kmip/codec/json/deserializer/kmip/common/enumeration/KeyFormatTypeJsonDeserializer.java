package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeJsonDeserializer extends AbstractKmipJsonDeserializer<KeyFormatType, String> {

    public KeyFormatTypeJsonDeserializer() {
        super(KeyFormatType.kmipTag, KeyFormatType.encodingType, String.class, value -> new KeyFormatType(KeyFormatType.fromName(value)));
    }
}