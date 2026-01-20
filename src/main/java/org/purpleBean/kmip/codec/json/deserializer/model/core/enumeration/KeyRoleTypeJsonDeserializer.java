package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;

public class KeyRoleTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyRoleType, String> {

    public KeyRoleTypeJsonDeserializer() {
        super(KeyRoleType.kmipTag, KeyRoleType.encodingType, String.class, value -> KeyRoleType.fromName(value).inst());
    }
}