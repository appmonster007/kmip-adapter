package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.G;

import java.io.IOException;
import java.math.BigInteger;

public class GXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<G, G.GBuilder> {

    public GXmlDeserializer() {
        super(G.kmipTag, G.encodingType);
    }

    @Override
    protected G.GBuilder createBuilder() {
        return G.builder();
    }

    @Override
    protected void setValue(G.GBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, BigInteger.class));
    }

    @Override
    protected G build(G.GBuilder builder) {
        return builder.build();
    }
}