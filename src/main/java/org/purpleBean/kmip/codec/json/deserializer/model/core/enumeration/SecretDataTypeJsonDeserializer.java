package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

public class SecretDataTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SecretDataType, String> {

    public SecretDataTypeJsonDeserializer() {
        super(SecretDataType.kmipTag, SecretDataType.encodingType, String.class, value -> new SecretDataType(SecretDataType.fromName(value)));
    }
}