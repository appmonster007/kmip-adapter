package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SecretDataType, String> {

    public SecretDataTypeJsonSerializer() {
        super(SecretDataType::getDescription);
    }
}