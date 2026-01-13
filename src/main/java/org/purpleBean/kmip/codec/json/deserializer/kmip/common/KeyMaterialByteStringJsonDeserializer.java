package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringJsonDeserializer() {
        super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType, ByteBuffer.class, value -> KeyMaterialByteString.builder().value(value).build());
    }
}