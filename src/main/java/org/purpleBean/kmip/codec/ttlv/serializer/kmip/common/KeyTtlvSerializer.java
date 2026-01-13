package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Key;

import java.nio.ByteBuffer;

public class KeyTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Key, ByteBuffer> {

    public KeyTtlvSerializer() {
        super(Key::getValue);
    }
}