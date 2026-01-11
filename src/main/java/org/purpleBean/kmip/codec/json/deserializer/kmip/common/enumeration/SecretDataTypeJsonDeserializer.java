package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeJsonDeserializer extends AbstractKmipJsonDeserializer<SecretDataType, String> {

    public SecretDataTypeJsonDeserializer() {
        super(SecretDataType.kmipTag, SecretDataType.encodingType, String.class, value -> new SecretDataType(SecretDataType.fromName(value)));
    }
}