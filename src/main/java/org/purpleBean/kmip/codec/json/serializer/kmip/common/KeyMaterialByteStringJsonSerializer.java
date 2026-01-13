package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringJsonSerializer() {
        super(KeyMaterialByteString::getValue);
    }
}