package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringTtlvDeserializer() {
        super(KeyValueByteString.kmipTag, KeyValueByteString.encodingType, ByteBuffer.class, value -> KeyValueByteString.builder().value(value).build());
    }
}