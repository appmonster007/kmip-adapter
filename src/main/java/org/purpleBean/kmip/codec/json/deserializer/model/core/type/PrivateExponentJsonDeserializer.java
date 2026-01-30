package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateExponent;

import java.io.IOException;
import java.math.BigInteger;

public class PrivateExponentJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PrivateExponent, PrivateExponent.PrivateExponentBuilder> {

    public PrivateExponentJsonDeserializer() {
        super(PrivateExponent.kmipTag, PrivateExponent.encodingType);
    }

    @Override
    protected PrivateExponent.PrivateExponentBuilder createBuilder() {
        return PrivateExponent.builder();
    }

    @Override
    protected void setValue(PrivateExponent.PrivateExponentBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, BigInteger.class));
    }

    @Override
    protected PrivateExponent build(PrivateExponent.PrivateExponentBuilder builder) {
        return builder.build();
    }
}
