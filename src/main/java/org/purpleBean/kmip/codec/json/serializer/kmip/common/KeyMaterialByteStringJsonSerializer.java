package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringJsonSerializer extends AbstractKmipJsonSerializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringJsonSerializer() {
        super(KeyMaterialByteString::getValue);
    }
}