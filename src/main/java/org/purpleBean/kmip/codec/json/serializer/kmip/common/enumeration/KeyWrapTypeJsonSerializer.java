package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyWrapType, String> {

    public KeyWrapTypeJsonSerializer() {
        super(KeyWrapType::getDescription);
    }
}