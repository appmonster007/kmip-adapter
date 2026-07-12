package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CreateOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CreateOpRequestPayload, CreateOpRequestPayload.CreateOpRequestPayloadBuilder> {

    public CreateOpRequestPayloadTtlvDeserializer() {
        super(CreateOpRequestPayload.kmipTag, CreateOpRequestPayload.encodingType);
    }

    @Override
    protected CreateOpRequestPayload.CreateOpRequestPayloadBuilder createBuilder() {
        return CreateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateOpRequestPayload build(CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}