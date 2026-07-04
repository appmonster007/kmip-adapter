package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogOpRequestPayload;

import java.io.IOException;

public class LogOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<LogOpRequestPayload, LogOpRequestPayload.LogOpRequestPayloadBuilder> {

    public LogOpRequestPayloadJsonDeserializer() {
        super(LogOpRequestPayload.kmipTag, LogOpRequestPayload.encodingType);
    }

    @Override
    protected LogOpRequestPayload.LogOpRequestPayloadBuilder createBuilder() {
        return LogOpRequestPayload.builder();
    }

    @Override
    protected void setValue(LogOpRequestPayload.LogOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.LOG_MESSAGE -> builder.logMessage(ctxt.readValue(p, LogMessage.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected LogOpRequestPayload build(LogOpRequestPayload.LogOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}