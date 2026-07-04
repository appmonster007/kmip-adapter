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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.InteropOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class InteropOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InteropOpRequestPayload, InteropOpRequestPayload.InteropOpRequestPayloadBuilder> {

    public InteropOpRequestPayloadTtlvDeserializer() {
        super(InteropOpRequestPayload.kmipTag, InteropOpRequestPayload.encodingType);
    }

    @Override
    protected InteropOpRequestPayload.InteropOpRequestPayloadBuilder createBuilder() {
        return InteropOpRequestPayload.builder();
    }

    @Override
    protected void setValue(InteropOpRequestPayload.InteropOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.INTEROP_FUNCTION -> builder.interopFunction(mapper.readValue(p, InteropFunction.class));
            case KmipTag.Standard.INTEROP_IDENTIFIER -> builder.interopIdentifier(mapper.readValue(p, InteropIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected InteropOpRequestPayload build(InteropOpRequestPayload.InteropOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}