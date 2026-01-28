package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;

public class KeyWrapTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyWrapType, Integer> {

    public KeyWrapTypeTtlvSerializer() {
        super(KeyWrapType::getIntValue);
    }
}