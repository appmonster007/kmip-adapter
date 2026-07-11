package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.InteropOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class InteropOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InteropOpResponsePayload, InteropOpResponsePayload.InteropOpResponsePayloadBuilder> {

    public InteropOpResponsePayloadTtlvDeserializer() {
        super(InteropOpResponsePayload.kmipTag, InteropOpResponsePayload.encodingType);
    }

    @Override
    protected InteropOpResponsePayload.InteropOpResponsePayloadBuilder createBuilder() {
        return InteropOpResponsePayload.builder();
    }

    @Override
    protected void setValue(InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected InteropOpResponsePayload build(InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}