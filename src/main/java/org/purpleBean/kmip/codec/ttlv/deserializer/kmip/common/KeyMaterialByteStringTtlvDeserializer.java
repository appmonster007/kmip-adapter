package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringTtlvDeserializer extends AbstractKmipTtlvDeserializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringTtlvDeserializer() {
        super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType, ByteBuffer.class, value -> KeyMaterialByteString.builder().value(value).build());
    }
}