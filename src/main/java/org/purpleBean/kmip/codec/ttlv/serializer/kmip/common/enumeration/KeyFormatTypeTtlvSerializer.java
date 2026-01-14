package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyFormatType, Integer> {

    public KeyFormatTypeTtlvSerializer() {
        super(KeyFormatType::getValue);
    }
}