package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Salt;

import java.nio.ByteBuffer;

public class SaltJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Salt, ByteBuffer> {

    public SaltJsonDeserializer() {
        super(Salt.kmipTag, Salt.encodingType, ByteBuffer.class, value -> Salt.builder().value(value).build());
    }
}