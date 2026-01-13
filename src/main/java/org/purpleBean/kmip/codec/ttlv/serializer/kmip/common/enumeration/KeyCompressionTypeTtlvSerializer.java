package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

public class KeyCompressionTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyCompressionType, Integer> {

    public KeyCompressionTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}