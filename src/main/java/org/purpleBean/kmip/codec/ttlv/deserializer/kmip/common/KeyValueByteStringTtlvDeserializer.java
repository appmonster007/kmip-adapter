package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringTtlvDeserializer extends AbstractKmipTtlvDeserializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringTtlvDeserializer() {
        super(KeyValueByteString.kmipTag, KeyValueByteString.encodingType, ByteBuffer.class, value -> KeyValueByteString.builder().value(value).build());
    }
}