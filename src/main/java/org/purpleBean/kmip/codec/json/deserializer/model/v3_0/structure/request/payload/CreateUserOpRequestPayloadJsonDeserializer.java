package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateUserOpRequestPayload;

import java.io.IOException;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

public class CreateUserOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CreateUserOpRequestPayload, CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder> {

    public CreateUserOpRequestPayloadJsonDeserializer() {
        super(CreateUserOpRequestPayload.kmipTag, CreateUserOpRequestPayload.encodingType);
    }

    @Override
    protected CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder createBuilder() {
        return CreateUserOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateUserOpRequestPayload build(CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}