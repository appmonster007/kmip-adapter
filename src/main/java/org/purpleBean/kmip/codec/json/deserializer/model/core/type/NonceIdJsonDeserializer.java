package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.NonceId;

import java.nio.ByteBuffer;

public class NonceIdJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NonceId, ByteBuffer> {

    public NonceIdJsonDeserializer() {
        super(NonceId.kmipTag, NonceId.encodingType, ByteBuffer.class, value -> NonceId.builder().value(value).build());
    }
}