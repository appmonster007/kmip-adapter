package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Ephemeral;

import java.io.IOException;

public class EphemeralXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Ephemeral, Ephemeral.EphemeralBuilder> {

    public EphemeralXmlDeserializer() {
        super(Ephemeral.kmipTag, Ephemeral.encodingType);
    }

    @Override
    protected Ephemeral.EphemeralBuilder createBuilder() {
        return Ephemeral.builder();
    }

    @Override
    protected void setValue(Ephemeral.EphemeralBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(Ephemeral.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected Ephemeral build(Ephemeral.EphemeralBuilder builder) {
        return builder.build();
    }
}