package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyFormatType, Integer> {

    public KeyFormatTypeTtlvSerializer() {
        super(KeyFormatType::getIntValue);
    }
}