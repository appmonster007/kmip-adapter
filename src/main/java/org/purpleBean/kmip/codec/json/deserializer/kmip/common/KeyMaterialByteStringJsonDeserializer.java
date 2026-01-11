package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringJsonDeserializer extends AbstractKmipJsonDeserializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringJsonDeserializer() {
        super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType, ByteBuffer.class, value -> KeyMaterialByteString.builder().value(value).build());
    }
}