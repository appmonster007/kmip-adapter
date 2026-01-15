package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringJsonSerializer() {
        super(KeyMaterialByteString::getValue);
    }
}