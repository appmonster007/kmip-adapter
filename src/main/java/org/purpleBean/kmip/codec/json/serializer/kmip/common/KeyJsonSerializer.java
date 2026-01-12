package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Key;

import java.nio.ByteBuffer;

public class KeyJsonSerializer extends AbstractKmipJsonSerializer<Key, ByteBuffer> {

    public KeyJsonSerializer() {
        super(Key::getValue);
    }
}