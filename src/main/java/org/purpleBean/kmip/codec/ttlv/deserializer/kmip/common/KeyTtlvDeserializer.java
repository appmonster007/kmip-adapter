package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.Key;

import java.nio.ByteBuffer;

public class KeyTtlvDeserializer extends AbstractKmipTtlvDeserializer<Key, ByteBuffer> {

    public KeyTtlvDeserializer() {
        super(Key.kmipTag, Key.encodingType, ByteBuffer.class, value -> Key.builder().value(value).build());
    }
}