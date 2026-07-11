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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.PingOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PingOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PingOpRequestPayload, PingOpRequestPayload.PingOpRequestPayloadBuilder> {

    public PingOpRequestPayloadTtlvDeserializer() {
        super(PingOpRequestPayload.kmipTag, PingOpRequestPayload.encodingType);
    }

    @Override
    protected PingOpRequestPayload.PingOpRequestPayloadBuilder createBuilder() {
        return PingOpRequestPayload.builder();
    }

    @Override
    protected void setValue(PingOpRequestPayload.PingOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected PingOpRequestPayload build(PingOpRequestPayload.PingOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}