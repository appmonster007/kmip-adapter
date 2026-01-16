package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.nio.ByteBuffer;

public class NonceValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NonceValue, ByteBuffer> {

    public NonceValueJsonDeserializer() {
        super(NonceValue.kmipTag, NonceValue.encodingType, ByteBuffer.class, value -> NonceValue.builder().value(value).build());
    }
}