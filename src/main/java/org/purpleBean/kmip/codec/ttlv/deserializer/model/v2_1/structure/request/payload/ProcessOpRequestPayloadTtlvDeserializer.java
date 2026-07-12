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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ProcessOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class ProcessOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProcessOpRequestPayload, ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder> {

    public ProcessOpRequestPayloadTtlvDeserializer() {
        super(ProcessOpRequestPayload.kmipTag, ProcessOpRequestPayload.encodingType);
    }

    @Override
    protected ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder createBuilder() {
        return ProcessOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(mapper.readValue(p, AsynchronousCorrelationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ProcessOpRequestPayload build(ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}