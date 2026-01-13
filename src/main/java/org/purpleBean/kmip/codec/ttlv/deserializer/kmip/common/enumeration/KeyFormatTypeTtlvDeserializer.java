package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyFormatType, Integer> {

    public KeyFormatTypeTtlvDeserializer() {
        super(KeyFormatType.kmipTag, KeyFormatType.encodingType, Integer.class, value -> new KeyFormatType(KeyFormatType.fromValue(value)));
    }
}