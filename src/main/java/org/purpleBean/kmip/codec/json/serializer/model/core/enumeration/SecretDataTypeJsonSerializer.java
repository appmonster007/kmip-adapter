package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

public class SecretDataTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SecretDataType, String> {

    public SecretDataTypeJsonSerializer() {
        super(SecretDataType::getDescription);
    }
}