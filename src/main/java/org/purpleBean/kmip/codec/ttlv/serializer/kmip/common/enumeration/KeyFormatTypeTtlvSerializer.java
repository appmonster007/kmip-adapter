package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeTtlvSerializer extends AbstractKmipTtlvSerializer<KeyFormatType, Integer> {

    public KeyFormatTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}