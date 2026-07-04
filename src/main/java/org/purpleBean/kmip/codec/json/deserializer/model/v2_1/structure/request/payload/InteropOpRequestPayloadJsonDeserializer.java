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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.InteropOpRequestPayload;

import java.io.IOException;

public class InteropOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InteropOpRequestPayload, InteropOpRequestPayload.InteropOpRequestPayloadBuilder> {

    public InteropOpRequestPayloadJsonDeserializer() {
        super(InteropOpRequestPayload.kmipTag, InteropOpRequestPayload.encodingType);
    }

    @Override
    protected InteropOpRequestPayload.InteropOpRequestPayloadBuilder createBuilder() {
        return InteropOpRequestPayload.builder();
    }

    @Override
    protected void setValue(InteropOpRequestPayload.InteropOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.INTEROP_FUNCTION -> builder.interopFunction(ctxt.readValue(p, InteropFunction.class));
            case KmipTag.Standard.INTEROP_IDENTIFIER -> builder.interopIdentifier(ctxt.readValue(p, InteropIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected InteropOpRequestPayload build(InteropOpRequestPayload.InteropOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}