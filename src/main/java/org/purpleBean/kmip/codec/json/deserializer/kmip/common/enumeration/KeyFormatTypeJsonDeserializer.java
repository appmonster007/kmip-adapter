package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyFormatType, String> {

    public KeyFormatTypeJsonDeserializer() {
        super(KeyFormatType.kmipTag, KeyFormatType.encodingType, String.class, value -> new KeyFormatType(KeyFormatType.fromName(value)));
    }
}