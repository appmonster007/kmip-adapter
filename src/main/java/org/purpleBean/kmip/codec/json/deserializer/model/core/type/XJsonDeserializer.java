package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.X;

import java.io.IOException;
import java.math.BigInteger;

public class XJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<X, X.XBuilder> {

    public XJsonDeserializer() {
        super(X.kmipTag, X.encodingType);
    }

    @Override
    protected X.XBuilder createBuilder() {
        return X.builder();
    }

    @Override
    protected void setValue(X.XBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, BigInteger.class));
    }

    @Override
    protected X build(X.XBuilder builder) {
        return builder.build();
    }
}
