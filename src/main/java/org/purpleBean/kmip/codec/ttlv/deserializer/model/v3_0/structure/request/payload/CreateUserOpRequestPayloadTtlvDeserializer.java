package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.request.payload;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateUserOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

public class CreateUserOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CreateUserOpRequestPayload, CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder> {

    public CreateUserOpRequestPayloadTtlvDeserializer() {
        super(CreateUserOpRequestPayload.kmipTag, CreateUserOpRequestPayload.encodingType);
    }

    @Override
    protected CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder createBuilder() {
        return CreateUserOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateUserOpRequestPayload build(CreateUserOpRequestPayload.CreateUserOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}