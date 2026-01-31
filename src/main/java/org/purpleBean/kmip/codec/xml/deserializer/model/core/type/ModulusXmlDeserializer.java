package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Modulus;

import java.io.IOException;
import java.math.BigInteger;

public class ModulusXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Modulus, Modulus.ModulusBuilder> {

    public ModulusXmlDeserializer() {
        super(Modulus.kmipTag, Modulus.encodingType);
    }

    @Override
    protected Modulus.ModulusBuilder createBuilder() {
        return Modulus.builder();
    }

    @Override
    protected void setValue(Modulus.ModulusBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, BigInteger.class));
    }

    @Override
    protected Modulus build(Modulus.ModulusBuilder builder) {
        return builder.build();
    }
}