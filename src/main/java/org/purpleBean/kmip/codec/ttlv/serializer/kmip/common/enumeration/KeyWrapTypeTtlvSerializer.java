package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeTtlvSerializer extends AbstractKmipTtlvSerializer<KeyWrapType, Integer> {

    public KeyWrapTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}