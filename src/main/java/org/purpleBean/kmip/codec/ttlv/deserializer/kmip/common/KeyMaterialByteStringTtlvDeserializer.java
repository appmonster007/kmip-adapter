package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringTtlvDeserializer() {
        super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType, ByteBuffer.class, value -> KeyMaterialByteString.builder().value(value).build());
    }
}