package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Key;

import java.nio.ByteBuffer;

public class KeyJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Key, ByteBuffer> {

    public KeyJsonDeserializer() {
        super(Key.kmipTag, Key.encodingType, ByteBuffer.class, value -> Key.builder().value(value).build());
    }
}