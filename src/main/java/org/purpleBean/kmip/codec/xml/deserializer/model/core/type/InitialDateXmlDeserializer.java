package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.InitialDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class InitialDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<InitialDate, InitialDate.InitialDateBuilder> {

    public InitialDateXmlDeserializer() {
        super(InitialDate.kmipTag, InitialDate.encodingType);
    }

    @Override
    protected InitialDate.InitialDateBuilder createBuilder() {
        return InitialDate.builder();
    }

    @Override
    protected void setValue(InitialDate.InitialDateBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected InitialDate build(InitialDate.InitialDateBuilder builder) {
        return builder.build();
    }
}