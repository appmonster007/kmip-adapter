package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.D;

import java.io.IOException;
import java.math.BigInteger;

public class DXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<D, D.DBuilder> {

    public DXmlDeserializer() {
        super(D.kmipTag, D.encodingType);
    }

    @Override
    protected D.DBuilder createBuilder() {
        return D.builder();
    }

    @Override
    protected void setValue(D.DBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, BigInteger.class));
    }

    @Override
    protected D build(D.DBuilder builder) {
        return builder.build();
    }
}