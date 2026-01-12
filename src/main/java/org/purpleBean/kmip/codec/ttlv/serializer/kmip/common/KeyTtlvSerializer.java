package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Key;

import java.nio.ByteBuffer;

public class KeyTtlvSerializer extends AbstractKmipTtlvSerializer<Key, ByteBuffer> {

    public KeyTtlvSerializer() {
        super(Key::getValue);
    }
}