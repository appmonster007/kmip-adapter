package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.Key;

import java.nio.ByteBuffer;

public class KeyJsonDeserializer extends AbstractKmipJsonDeserializer<Key, ByteBuffer> {

    public KeyJsonDeserializer() {
        super(Key.kmipTag, Key.encodingType, ByteBuffer.class, value -> Key.builder().value(value).build());
    }
}