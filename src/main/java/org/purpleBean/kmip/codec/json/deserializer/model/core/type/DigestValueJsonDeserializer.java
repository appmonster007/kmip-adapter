package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DigestValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DigestValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DigestValue, DigestValue.DigestValueBuilder> {

    public DigestValueJsonDeserializer() {
        super(DigestValue.kmipTag, DigestValue.encodingType);
    }

    @Override
    protected DigestValue.DigestValueBuilder createBuilder() {
        return DigestValue.builder();
    }

    @Override
    protected void setValue(DigestValue.DigestValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected DigestValue build(DigestValue.DigestValueBuilder builder) {
        return builder.build();
    }
}
