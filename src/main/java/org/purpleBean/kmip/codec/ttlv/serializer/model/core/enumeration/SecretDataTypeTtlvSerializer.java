package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

public class SecretDataTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SecretDataType, Integer> {

    public SecretDataTypeTtlvSerializer() {
        super(SecretDataType::getValue);
    }
}