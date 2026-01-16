package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.nio.ByteBuffer;

public class NonceValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NonceValue, ByteBuffer> {

    public NonceValueJsonSerializer() {
        super(NonceValue::getValue);
    }
}