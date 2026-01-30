package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

import java.io.IOException;

public class CryptographicLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CryptographicLength, CryptographicLength.CryptographicLengthBuilder> {

    public CryptographicLengthJsonDeserializer() {
        super(CryptographicLength.kmipTag, CryptographicLength.encodingType);
    }

    @Override
    protected CryptographicLength.CryptographicLengthBuilder createBuilder() {
        return CryptographicLength.builder();
    }

    @Override
    protected void setValue(CryptographicLength.CryptographicLengthBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected CryptographicLength build(CryptographicLength.CryptographicLengthBuilder builder) {
        return builder.build();
    }
}
