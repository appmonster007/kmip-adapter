package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.NonceId;

import java.nio.ByteBuffer;

public class NonceIdTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NonceId, ByteBuffer> {

    public NonceIdTtlvDeserializer() {
        super(NonceId.kmipTag, NonceId.encodingType, ByteBuffer.class, value -> NonceId.builder().value(value).build());
    }
}