package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3_0.type.OtpCounter;

import java.io.IOException;

public class OtpCounterXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<OtpCounter, OtpCounter.OtpCounterBuilder> {

    public OtpCounterXmlDeserializer() {
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
