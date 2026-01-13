package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SecretDataType, Integer> {

    public SecretDataTypeTtlvDeserializer() {
        super(SecretDataType.kmipTag, SecretDataType.encodingType, Integer.class, value -> new SecretDataType(SecretDataType.fromValue(value)));
    }
}