package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeJsonDeserializer extends AbstractKmipJsonDeserializer<KeyRoleType, String> {

    public KeyRoleTypeJsonDeserializer() {
        super(KeyRoleType.kmipTag, KeyRoleType.encodingType, String.class, value -> new KeyRoleType(KeyRoleType.fromName(value)));
    }
}