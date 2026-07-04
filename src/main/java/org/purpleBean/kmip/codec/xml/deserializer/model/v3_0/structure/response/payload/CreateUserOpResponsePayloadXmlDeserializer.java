package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.structure.response.payload;

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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateUserOpResponsePayload;

import java.io.IOException;

public class CreateUserOpResponsePayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CreateUserOpResponsePayload, CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder> {

    public CreateUserOpResponsePayloadXmlDeserializer() {
        super(CreateUserOpResponsePayload.kmipTag, CreateUserOpResponsePayload.encodingType);
    }

    @Override
    protected CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder createBuilder() {
        return CreateUserOpResponsePayload.builder();
    }

    @Override
    protected void setValue(CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateUserOpResponsePayload build(CreateUserOpResponsePayload.CreateUserOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}