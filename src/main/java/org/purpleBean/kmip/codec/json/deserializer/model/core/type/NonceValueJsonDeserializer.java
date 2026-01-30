package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NonceValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NonceValue, NonceValue.NonceValueBuilder> {

    public NonceValueJsonDeserializer() {
        super(NonceValue.kmipTag, NonceValue.encodingType);
    }

    @Override
    protected NonceValue.NonceValueBuilder createBuilder() {
        return NonceValue.builder();
    }

    @Override
    protected void setValue(NonceValue.NonceValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected NonceValue build(NonceValue.NonceValueBuilder builder) {
        return builder.build();
    }
}
