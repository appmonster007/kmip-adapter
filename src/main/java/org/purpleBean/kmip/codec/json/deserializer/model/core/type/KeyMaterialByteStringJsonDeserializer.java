package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringJsonDeserializer() {
        super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType, ByteBuffer.class, value -> KeyMaterialByteString.builder().value(value).build());
    }
}