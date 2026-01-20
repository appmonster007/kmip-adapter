package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;

public class KeyRoleTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyRoleType, Integer> {

    public KeyRoleTypeTtlvDeserializer() {
        super(KeyRoleType.kmipTag, KeyRoleType.encodingType, Integer.class, value -> KeyRoleType.fromValue(value).inst());
    }
}