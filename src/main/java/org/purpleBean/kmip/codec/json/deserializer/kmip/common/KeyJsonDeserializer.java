package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Key;

import java.nio.ByteBuffer;

public class KeyJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Key, ByteBuffer> {

    public KeyJsonDeserializer() {
        super(Key.kmipTag, Key.encodingType, ByteBuffer.class, value -> Key.builder().value(value).build());
    }
}