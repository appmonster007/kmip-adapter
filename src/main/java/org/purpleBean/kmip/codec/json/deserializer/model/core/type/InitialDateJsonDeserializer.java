package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.InitialDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class InitialDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InitialDate, InitialDate.InitialDateBuilder> {

    public InitialDateJsonDeserializer() {
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
