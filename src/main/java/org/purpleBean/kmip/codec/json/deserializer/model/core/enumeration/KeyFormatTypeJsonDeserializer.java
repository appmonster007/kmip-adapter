package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyFormatType, String> {

    public KeyFormatTypeJsonDeserializer() {
        super(KeyFormatType.kmipTag, KeyFormatType.encodingType, String.class, value -> new KeyFormatType(KeyFormatType.fromName(value)));
    }
}