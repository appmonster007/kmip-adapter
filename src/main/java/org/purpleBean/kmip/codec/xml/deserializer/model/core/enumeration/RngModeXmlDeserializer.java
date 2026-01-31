package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RngMode;

import java.io.IOException;

public class RngModeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RngMode, RngMode.RngModeBuilder> {

    public RngModeXmlDeserializer() {
        super(RngMode.kmipTag, RngMode.encodingType);
    }

    @Override
    protected RngMode.RngModeBuilder createBuilder() {
        return RngMode.builder();
    }

    @Override
    protected void setValue(RngMode.RngModeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(RngMode.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected RngMode build(RngMode.RngModeBuilder builder) {
        return builder.build();
    }
}