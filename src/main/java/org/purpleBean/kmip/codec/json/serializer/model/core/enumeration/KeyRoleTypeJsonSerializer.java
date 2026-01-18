package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;

public class KeyRoleTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyRoleType, String> {

    public KeyRoleTypeJsonSerializer() {
        super(KeyRoleType::getDescription);
    }
}