package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.Salt;

import java.nio.ByteBuffer;

public class SaltTtlvDeserializer extends AbstractKmipTtlvDeserializer<Salt, ByteBuffer> {

    public SaltTtlvDeserializer() {
        super(Salt.kmipTag, Salt.encodingType, ByteBuffer.class, value -> Salt.builder().value(value).build());
    }
}