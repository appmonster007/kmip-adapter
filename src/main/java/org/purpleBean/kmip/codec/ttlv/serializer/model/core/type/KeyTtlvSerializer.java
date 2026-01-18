package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.Key;

import java.nio.ByteBuffer;

public class KeyTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Key, ByteBuffer> {

    public KeyTtlvSerializer() {
        super(Key::getValue);
    }
}