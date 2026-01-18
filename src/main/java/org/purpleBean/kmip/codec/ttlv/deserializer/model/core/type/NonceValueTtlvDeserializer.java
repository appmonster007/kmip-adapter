package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.nio.ByteBuffer;

public class NonceValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NonceValue, ByteBuffer> {

    public NonceValueTtlvDeserializer() {
        super(NonceValue.kmipTag, NonceValue.encodingType, ByteBuffer.class, value -> NonceValue.builder().value(value).build());
    }
}