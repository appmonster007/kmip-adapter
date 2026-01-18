package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringTtlvSerializer() {
        super(KeyValueByteString::getValue);
    }
}