package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.LogMessage;

import java.io.IOException;

public class LogMessageXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LogMessage, LogMessage.LogMessageBuilder> {

    public LogMessageXmlDeserializer() {
        super(LogMessage.kmipTag, LogMessage.encodingType);
    }

    @Override
    protected LogMessage.LogMessageBuilder createBuilder() {
        return LogMessage.builder();
    }

    @Override
    protected void setValue(LogMessage.LogMessageBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected LogMessage build(LogMessage.LogMessageBuilder builder) {
        return builder.build();
    }
}
