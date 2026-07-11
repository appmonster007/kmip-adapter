package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LogoutOpResponsePayload;

import java.io.IOException;

public class LogoutOpResponsePayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LogoutOpResponsePayload, LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder> {

    public LogoutOpResponsePayloadXmlDeserializer() {
        super(LogoutOpResponsePayload.kmipTag, LogoutOpResponsePayload.encodingType);
    }

    @Override
    protected LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder createBuilder() {
        return LogoutOpResponsePayload.builder();
    }

    @Override
    protected void setValue(LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected LogoutOpResponsePayload build(LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}