package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeTtlvSerializer extends AbstractKmipTtlvSerializer<KeyRoleType, Integer> {

    public KeyRoleTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}