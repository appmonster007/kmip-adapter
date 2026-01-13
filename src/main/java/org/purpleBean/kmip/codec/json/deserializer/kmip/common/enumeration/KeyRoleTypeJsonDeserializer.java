package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyRoleType, String> {

    public KeyRoleTypeJsonDeserializer() {
        super(KeyRoleType.kmipTag, KeyRoleType.encodingType, String.class, value -> new KeyRoleType(KeyRoleType.fromName(value)));
    }
}