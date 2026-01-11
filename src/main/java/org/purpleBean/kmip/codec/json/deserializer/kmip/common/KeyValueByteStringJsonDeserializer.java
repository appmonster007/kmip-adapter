package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringJsonDeserializer extends AbstractKmipJsonDeserializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringJsonDeserializer() {
        super(KeyValueByteString.kmipTag, KeyValueByteString.encodingType, ByteBuffer.class, value -> KeyValueByteString.builder().value(value).build());
    }
}