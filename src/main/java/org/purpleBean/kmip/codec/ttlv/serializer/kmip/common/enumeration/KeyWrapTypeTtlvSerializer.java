package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyWrapType, Integer> {

    public KeyWrapTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}