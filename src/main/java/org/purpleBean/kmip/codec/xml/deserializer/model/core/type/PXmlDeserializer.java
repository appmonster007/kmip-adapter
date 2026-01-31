package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.P;

import java.io.IOException;
import java.math.BigInteger;

public class PXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<P, P.PBuilder> {

    public PXmlDeserializer() {
        super(P.kmipTag, P.encodingType);
    }

    @Override
    protected P.PBuilder createBuilder() {
        return P.builder();
    }

    @Override
    protected void setValue(P.PBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, BigInteger.class));
    }

    @Override
    protected P build(P.PBuilder builder) {
        return builder.build();
    }
}