package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.LogMessage;

import java.io.IOException;

public class LogMessageJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<LogMessage, LogMessage.LogMessageBuilder> {

    public LogMessageJsonDeserializer() {
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
