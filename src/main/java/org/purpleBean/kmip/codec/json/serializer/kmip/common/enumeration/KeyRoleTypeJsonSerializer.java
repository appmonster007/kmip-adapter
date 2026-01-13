package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyRoleType, String> {

    public KeyRoleTypeJsonSerializer() {
        super(KeyRoleType::getDescription);
    }
}