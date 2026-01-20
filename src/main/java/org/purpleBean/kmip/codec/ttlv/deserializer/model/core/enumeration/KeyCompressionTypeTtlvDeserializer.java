package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;

public class KeyCompressionTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyCompressionType, Integer> {

    public KeyCompressionTypeTtlvDeserializer() {
        super(KeyCompressionType.kmipTag, KeyCompressionType.encodingType, Integer.class, value -> KeyCompressionType.fromValue(value).inst());
    }
}