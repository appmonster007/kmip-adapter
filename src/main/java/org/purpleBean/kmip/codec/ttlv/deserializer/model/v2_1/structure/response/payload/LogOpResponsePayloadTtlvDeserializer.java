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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LogOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class LogOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LogOpResponsePayload, LogOpResponsePayload.LogOpResponsePayloadBuilder> {

    public LogOpResponsePayloadTtlvDeserializer() {
        super(LogOpResponsePayload.kmipTag, LogOpResponsePayload.encodingType);
    }

    @Override
    protected LogOpResponsePayload.LogOpResponsePayloadBuilder createBuilder() {
        return LogOpResponsePayload.builder();
    }

    @Override
    protected void setValue(LogOpResponsePayload.LogOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected LogOpResponsePayload build(LogOpResponsePayload.LogOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}