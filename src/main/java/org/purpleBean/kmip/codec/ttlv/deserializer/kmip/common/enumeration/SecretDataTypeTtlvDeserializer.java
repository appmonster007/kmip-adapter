package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<SecretDataType, Integer> {

    public SecretDataTypeTtlvDeserializer() {
        super(SecretDataType.kmipTag, SecretDataType.encodingType, Integer.class, value -> new SecretDataType(SecretDataType.fromValue(value)));
    }
}