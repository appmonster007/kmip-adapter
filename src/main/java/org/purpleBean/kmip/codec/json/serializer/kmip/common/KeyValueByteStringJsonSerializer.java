package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringJsonSerializer extends AbstractKmipJsonSerializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringJsonSerializer() {
        super(KeyValueByteString::getValue);
    }
}