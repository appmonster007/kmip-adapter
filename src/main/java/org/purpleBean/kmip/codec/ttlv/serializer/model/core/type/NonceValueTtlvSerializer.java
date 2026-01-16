package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.nio.ByteBuffer;

public class NonceValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<NonceValue, ByteBuffer> {

    public NonceValueTtlvSerializer() {
        super(NonceValue::getValue);
    }
}