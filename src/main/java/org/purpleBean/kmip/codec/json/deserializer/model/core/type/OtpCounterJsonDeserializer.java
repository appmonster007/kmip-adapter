package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.OtpCounter;

import java.io.IOException;

public class OtpCounterJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OtpCounter, OtpCounter.OtpCounterBuilder> {

    public OtpCounterJsonDeserializer() {
        super(OtpCounter.kmipTag, OtpCounter.encodingType);
    }

    @Override
    protected OtpCounter.OtpCounterBuilder createBuilder() {
        return OtpCounter.builder();
    }

    @Override
    protected void setValue(OtpCounter.OtpCounterBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected OtpCounter build(OtpCounter.OtpCounterBuilder builder) {
        return builder.build();
    }
}
