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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ProcessOpResponsePayload;

import java.io.IOException;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class ProcessOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProcessOpResponsePayload, ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder> {

    public ProcessOpResponsePayloadJsonDeserializer() {
        super(ProcessOpResponsePayload.kmipTag, ProcessOpResponsePayload.encodingType);
    }

    @Override
    protected ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder createBuilder() {
        return ProcessOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ProcessOpResponsePayload build(ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}