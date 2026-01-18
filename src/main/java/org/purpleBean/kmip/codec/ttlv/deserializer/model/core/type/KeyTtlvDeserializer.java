package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.Key;

import java.nio.ByteBuffer;

public class KeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Key, ByteBuffer> {

    public KeyTtlvDeserializer() {
        super(Key.kmipTag, Key.encodingType, ByteBuffer.class, value -> Key.builder().value(value).build());
    }
}