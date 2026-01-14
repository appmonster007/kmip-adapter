package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SecretDataType, Integer> {

    public SecretDataTypeTtlvSerializer() {
        super(SecretDataType::getValue);
    }
}