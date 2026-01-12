package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeJsonSerializer extends AbstractKmipJsonSerializer<KeyWrapType, String> {

    public KeyWrapTypeJsonSerializer() {
        super(KeyWrapType::getDescription);
    }
}