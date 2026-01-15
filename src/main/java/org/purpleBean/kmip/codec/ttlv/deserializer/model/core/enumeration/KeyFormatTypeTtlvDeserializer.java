package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyFormatType, Integer> {

    public KeyFormatTypeTtlvDeserializer() {
        super(KeyFormatType.kmipTag, KeyFormatType.encodingType, Integer.class, value -> new KeyFormatType(KeyFormatType.fromValue(value)));
    }
}