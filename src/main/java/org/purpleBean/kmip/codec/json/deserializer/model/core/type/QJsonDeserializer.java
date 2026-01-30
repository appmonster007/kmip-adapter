package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Q;

import java.io.IOException;
import java.math.BigInteger;

public class QJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Q, Q.QBuilder> {

    public QJsonDeserializer() {
        super(Q.kmipTag, Q.encodingType);
    }

    @Override
    protected Q.QBuilder createBuilder() {
        return Q.builder();
    }

    @Override
    protected void setValue(Q.QBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, BigInteger.class));
    }

    @Override
    protected Q build(Q.QBuilder builder) {
        return builder.build();
    }
}
