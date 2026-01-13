package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringJsonSerializer() {
        super(KeyValueByteString::getValue);
    }
}