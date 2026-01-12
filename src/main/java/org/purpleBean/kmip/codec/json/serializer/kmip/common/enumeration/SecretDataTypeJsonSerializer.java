package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeJsonSerializer extends AbstractKmipJsonSerializer<SecretDataType, String> {

    public SecretDataTypeJsonSerializer() {
        super(SecretDataType::getDescription);
    }
}