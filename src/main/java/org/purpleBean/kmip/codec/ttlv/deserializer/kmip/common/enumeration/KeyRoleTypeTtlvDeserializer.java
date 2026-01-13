package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyRoleType, Integer> {

    public KeyRoleTypeTtlvDeserializer() {
        super(KeyRoleType.kmipTag, KeyRoleType.encodingType, Integer.class, value -> new KeyRoleType(KeyRoleType.fromValue(value)));
    }
}