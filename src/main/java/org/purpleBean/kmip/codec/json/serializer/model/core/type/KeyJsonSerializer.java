package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.Key;

import java.nio.ByteBuffer;

public class KeyJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Key, ByteBuffer> {

    public KeyJsonSerializer() {
        super(Key::getValue);
    }
}