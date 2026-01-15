package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringTtlvSerializer() {
        super(KeyMaterialByteString::getValue);
    }
}