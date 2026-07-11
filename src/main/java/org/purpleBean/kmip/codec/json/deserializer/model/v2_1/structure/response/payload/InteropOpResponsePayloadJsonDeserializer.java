package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.InteropOpResponsePayload;

import java.io.IOException;

public class InteropOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InteropOpResponsePayload, InteropOpResponsePayload.InteropOpResponsePayloadBuilder> {

    public InteropOpResponsePayloadJsonDeserializer() {
        super(InteropOpResponsePayload.kmipTag, InteropOpResponsePayload.encodingType);
    }

    @Override
    protected InteropOpResponsePayload.InteropOpResponsePayloadBuilder createBuilder() {
        return InteropOpResponsePayload.builder();
    }

    @Override
    protected void setValue(InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected InteropOpResponsePayload build(InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}