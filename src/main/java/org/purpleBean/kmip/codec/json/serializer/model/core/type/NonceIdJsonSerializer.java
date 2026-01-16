package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.NonceId;

import java.nio.ByteBuffer;

public class NonceIdJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NonceId, ByteBuffer> {

    public NonceIdJsonSerializer() {
        super(NonceId::getValue);
    }
}