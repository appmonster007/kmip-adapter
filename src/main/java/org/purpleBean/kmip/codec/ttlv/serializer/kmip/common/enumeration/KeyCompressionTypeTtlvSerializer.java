package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

public class KeyCompressionTypeTtlvSerializer extends AbstractKmipTtlvSerializer<KeyCompressionType, Integer> {

    public KeyCompressionTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}