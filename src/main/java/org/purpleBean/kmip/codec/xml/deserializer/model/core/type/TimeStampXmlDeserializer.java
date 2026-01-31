package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.TimeStamp;

import java.io.IOException;
import java.time.OffsetDateTime;

public class TimeStampXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<TimeStamp, TimeStamp.TimeStampBuilder> {

    public TimeStampXmlDeserializer() {
        super(TimeStamp.kmipTag, TimeStamp.encodingType);
    }

    @Override
    protected TimeStamp.TimeStampBuilder createBuilder() {
        return TimeStamp.builder();
    }

    @Override
    protected void setValue(TimeStamp.TimeStampBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected TimeStamp build(TimeStamp.TimeStampBuilder builder) {
        return builder.build();
    }
}