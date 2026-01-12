package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeTtlvSerializer extends AbstractKmipTtlvSerializer<SecretDataType, Integer> {

    public SecretDataTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}