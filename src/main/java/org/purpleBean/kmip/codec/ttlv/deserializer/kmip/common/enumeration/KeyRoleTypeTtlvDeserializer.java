package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<KeyRoleType, Integer> {

    public KeyRoleTypeTtlvDeserializer() {
        super(KeyRoleType.kmipTag, KeyRoleType.encodingType, Integer.class, value -> new KeyRoleType(KeyRoleType.fromValue(value)));
    }
}