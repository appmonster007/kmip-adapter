package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.NonceId;

import java.nio.ByteBuffer;

public class NonceIdTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<NonceId, ByteBuffer> {

    public NonceIdTtlvSerializer() {
        super(NonceId::getValue);
    }
}