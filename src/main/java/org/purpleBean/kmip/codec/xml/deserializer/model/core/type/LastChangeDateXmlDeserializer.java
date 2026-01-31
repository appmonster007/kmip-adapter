package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.LastChangeDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class LastChangeDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LastChangeDate, LastChangeDate.LastChangeDateBuilder> {

    public LastChangeDateXmlDeserializer() {
        super(LastChangeDate.kmipTag, LastChangeDate.encodingType);
    }

    @Override
    protected LastChangeDate.LastChangeDateBuilder createBuilder() {
        return LastChangeDate.builder();
    }

    @Override
    protected void setValue(LastChangeDate.LastChangeDateBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected LastChangeDate build(LastChangeDate.LastChangeDateBuilder builder) {
        return builder.build();
    }
}