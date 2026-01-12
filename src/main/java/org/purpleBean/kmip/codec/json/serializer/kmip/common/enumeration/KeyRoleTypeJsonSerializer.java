package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeJsonSerializer extends AbstractKmipJsonSerializer<KeyRoleType, String> {

    public KeyRoleTypeJsonSerializer() {
        super(KeyRoleType::getDescription);
    }
}