package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;

public class KeyCompressionTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyCompressionType, Integer> {

    public KeyCompressionTypeTtlvSerializer() {
        super(KeyCompressionType::getValue);
    }
}