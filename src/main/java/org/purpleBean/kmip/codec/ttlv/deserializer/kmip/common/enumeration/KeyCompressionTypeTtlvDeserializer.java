package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

public class KeyCompressionTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<KeyCompressionType, Integer> {

    public KeyCompressionTypeTtlvDeserializer() {
        super(KeyCompressionType.kmipTag, KeyCompressionType.encodingType, Integer.class, value -> new KeyCompressionType(KeyCompressionType.fromValue(value)));
    }
}